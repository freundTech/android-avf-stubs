# Stubs for Android Virtualization Framework

The [Android Virtualization Framework](https://source.android.com/docs/core/virtualization) (AVF) is a set of libraries and services designed for running
secure virtual machines on Android devices. It was first introduced in Android 13 on some,
especially Pixel, devices and is required to be supported on any device launching with Android 15
or newer.

While Google seems to primarily intent AVF as a more portable alternative to TrustZone, it is also
capable of running full Linux virtual machines, as can be seen with the new Android 15/16 Terminal
app hidden in the developer settings.

Sadly at this point Google doesn't intend for third-party developers to use the AVF. The necessary
APIs aren't included in the Android SDK and launching a virtual machine requires a permission that
third-party apps can't request.
Luckily we can work around both of those problems. This repository provides stubs for the most
important AVF libraries, allowing you to link against them during development. Once installed on
your device your app will then use the real libraries installed there.
Additionally we can use `adb` to manually grant the necessary permission to our app.

The AVF is only officially supported on arm64 devices with some limited support on x86_64 devices
for easier development.


## Getting Started

The AVF allows you to run two types of virtual machines. Microdroid VMs run a minimal android
system. You can provide them with a binary payload that they execute in userspace after boot. Being
android based they have access to a few convenience features to make development easier, such as 
support for Android Binders and adb.

The other type of virtual machines are custom virtual machines. Here you can provide your own disk
image to run any software you like.


### Dependencies

Add the following dependency to your app. It is important to include it as `compileOnly`.
```kotlin
depencencies {
    compileOnly("io.freund.adrian.android.virtualmachine:stubs:1.0")
}
```

Additionally add the necessary permissions and features to your `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.MANAGE_VIRTUAL_MACHINE"/>
<uses-permission android:name="android.permission.USE_CUSTOM_VIRTUAL_MACHINE"/>

<uses-feature android:name="android.software.virtualization_framework" android:required="true"/>
```


### Building a binary payload

If you want to run a microdroid VM you can build the payload in the same project as your app.
If you want to run a custom VM look at Google's
[debian image build](https://android.googlesource.com/platform/packages/modules/Virtualization/+/refs/tags/android-16.0.0_r2/build/debian/).

To build a microdroid payload add the following code to your gradle configuration:
```kotlin
android {
    defaultConfig {
        externalNativeBuild {
            cmake {
                arguments += listOf("-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON")
            }
        }
        ndk {
            abiFilters += listOf("arm64-v8a", "x86_64")
        }
    }
    
    buildFeatures {
        prefab = true
    }
    
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    packaging {
        jniLibs {
            excludes += "**/libvm_payload.so"
        }
    }
}
```

Make sure to enable the `prefab` build feature to load native libraries from android library
dependencies. Also make sure to exclude the `libvm_payload.so` library from your APK. This is a stub
that should only be used for linking against. Shipping it with out app will prevent your payload
from running.

Next create the `src/main/cpp/CMakeLists.txt` file with the following content:
```cmake
cmake_minimum_required(VERSION 3.22.1)

project("mypayload")

add_library(${CMAKE_PROJECT_NAME} SHARED mypayload.cpp)

find_package(virtualmachine-stubs REQUIRED CONFIG)

target_link_libraries(${CMAKE_PROJECT_NAME}
    virtualmachine-stubs::vm_payload
    // Also add other dependencies like `log`
)
```

Microdroid payloads can only use a subset of the Android NDK API. For more details see the
[upstream readme](https://android.googlesource.com/platform/packages/modules/Virtualization/+/refs/tags/android-16.0.0_r2/libs/libvm_payload/README.md).

Lastly create your entrypoint in `mypayload.cpp` (or `.c` if you prefer):
```cpp
#include "vm_payload.h"

extern "C" int AVmPayload_main() {
    return 0;
}
```

### Starting a VM

To run a VM first check if you have the necessary permission. AVF has a bug where the VM storage
gets corrupted, preventing the VM from starting, if you tried to start it without the necessary
permission first.
```java
import android.content.pm.PackageManager;
import android.system.virtualmachine.VirtualMachine;

public static class VirtualMachineModel extends AndroidViewModel {
    public void run() {
        if (getApplication().checkSelfPermission(VirtualMachine.MANAGE_VIRTUAL_MACHINE_PERMISSION) == PackageManager.PERMISSION_DENIED) {
            Log.e(TAG, "Missing permission. Not starting");
            return;
        }
        
        /* ... */
    }
}
```

Next set up your callbacks and VM config:
```java
import android.system.virtualmachine.VirtualMachineCallback;
import android.system.virtualmachine.VirtualMachineConfig;
import android.system.virtualmachine.VirtualMachineManager;

public static class VirtualMachineModel extends AndroidViewModel {
    public void run() {
        /* ... */

        VirtualMachineCallback callback = new VirtualMachineCallback() {
            @Override
            public void onPayloadStarted(@NonNull VirtualMachine vm) {}

            @Override
            public void onPayloadReady(@NonNull VirtualMachine vm) {
                // This is where you would connect to your binder service running in the vm.
            }
            @Override
            public void onPayloadFinished(@NonNull VirtualMachine vm, int exitCode) {}
            @Override
            public void onError(@NonNull VirtualMachine vm, int errorCode, @NonNull String message) {}
            @Override
            public void onStopped(@NonNull VirtualMachine vm, int reason) {}
        };


        VirtualMachineConfig.Builder builder = new VirtualMachineConfig.Builder(getApplication());
        builder.setPayloadBinaryName("libmypayload.so");
        VirtualMachineConfig config = builder.build();
        
        /* ... */
    }
}
```

To run a custom VM build your config using `VirtualMachineConfig.Builder.setCustomImageConfig()`
instead.

Lastly start your virtual machine:

```java
import android.system.virtualmachine.VirtualMachine;
import android.system.virtualmachine.VirtualMachineException;

public static class VirtualMachineModel extends AndroidViewModel {
    /*
     * We need to keep a reference to our vm. If the VM object gets garbage collected the VM shuts
     * down.
     */
    private VirtualMachine virtualMachine;
    
    public void run() {
        /* ... */

        virtualMachine = vmm.getOrCreate("demo_vm", config);
        // If your config changed you need to recreate the VM
        try {
            mVirtualMachine.setConfig(config);
        } catch (VirtualMachineException e) {
            vmm.delete(VM_NAME);
            mVirtualMachine = vmm.create(VM_NAME, config);
        }
        virtualMachine.run();
        virtualMachine.setCallback(callback);
    }
}
```

For more detailed examples have a look at Google's
[MicroDroidDemoApp](https://android.googlesource.com/platform/packages/modules/Virtualization/+/refs/tags/android-16.0.0_r2/android/MicrodroidDemoApp/)
and
[TerminalApp](https://android.googlesource.com/platform/packages/modules/Virtualization/+/refs/tags/android-16.0.0_r2/android/TerminalApp/).

### Running

After building and installing your app you need go give your app the necessary permission. This can
be done in the adb shell using 
* `pm grant your.application.id android.permission.MANAGE_VIRTUAL_MACHINE`
* `pm grant your.application.id android.permission.USE_CUSTOM_VIRTUAL_MACHINE`

It might be possible to automate this on rooted phones or using
[Shizuku](https://github.com/RikkaApps/Shizuku).


# Copyright

These stubs are in large part a derivative work based on the original Android Virtualization
Framework code, which is licensed under the Apache License, Version 2.0.
As such they are also licensed under the Apache License, Version 2.0.

```
Copyright (C) 2021-2025 The Android Open Source Project
Copyright (C) 2025 Adrian Freund <adrian@freund.io>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

This project is not affiliated, associated, authorized, endorsed by, or in any way officially
connected with Alphabet Inc., Google LLC, The Android Open Source Project or any of its subsidiaries
or its affiliates.