/*
 * Copyright (C) 2021 The Android Open Source Project
 * Copyright (C) 2025 Adrian Freund <adrian@freund.io>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.system.virtualmachine;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.IntDef;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringDef;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Represents a configuration of a virtual machine. A configuration consists of hardware
 * configurations like the number of CPUs and the size of RAM, and software configurations like the
 * payload to run on the virtual machine.
 */
public class VirtualMachineConfig {
    public String findPayloadApk(PackageManager packageManager) throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DEBUG_LEVEL_NONE, DEBUG_LEVEL_FULL})
    public @interface DebugLevel {}

    /**
     * Not debuggable at all. No log is exported from the VM. Debugger can't be attached to the app
     * process running in the VM. This is the default level.
     */
    public static final int DEBUG_LEVEL_NONE = 0;

    /**
     * Fully debuggable. All logs (both logcat and kernel message) are exported. All processes
     * running in the VM can be attached to the debugger. Rooting is possible.
     */
    public static final int DEBUG_LEVEL_FULL = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CPU_TOPOLOGY_ONE_CPU, CPU_TOPOLOGY_MATCH_HOST})
    public @interface CpuTopology {}

    /**
     * Run VM with 1 vCPU. This is the default option, usually the fastest to boot and consuming the
     * least amount of resources. Typically the best option for small or ephemeral workloads.
     */
    public static final int CPU_TOPOLOGY_ONE_CPU = 0;

    /**
     * Run VM with vCPU topology matching the physical CPU topology of the host. Usually takes
     * longer to boot and consumes more resources compared to a single vCPU. Typically a good option
     * for long-running workloads that benefit from parallel execution.
     */
    public static final int CPU_TOPOLOGY_MATCH_HOST = 1;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({MICRODROID})
    private @interface OsName {}

    @OsName
    public static final String MICRODROID = "microdroid";

    /**
     * Returns the absolute path of the APK which should contain the binary payload that will
     * execute within the VM. Returns null if no specific path has been set.
     */
    @Nullable
    public String getApkPath() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns the package names of any extra APKs that have been requested for the VM. They are
     * returned in the order in which they were added via {@link Builder#addExtraApk}.
     */
    @NonNull
    public List<String> getExtraApks() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns the path within the APK to the payload config file that defines software aspects of
     * the VM.
     */
    @Nullable
    public String getPayloadConfigPath() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns the custom image config to launch the custom VM.
     */
    @Nullable
    public VirtualMachineCustomImageConfig getCustomImageConfig() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns the name of the payload binary file, in the {@code lib/<ABI>} directory of the APK,
     * that will be executed within the VM.
     */
    @Nullable
    public String getPayloadBinaryName() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns the debug level for the VM.
     */
    @DebugLevel
    public int getDebugLevel() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns whether the VM's memory will be protected from the host.
     */
    public boolean isProtectedVm() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns the amount of RAM that will be made available to the VM, or 0 if the default size
     * will be used.
     */
    @IntRange(from = 0)
    public long getMemoryBytes() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns the CPU topology configuration of the VM.
     */
    @CpuTopology
    public int getCpuTopology() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns whether encrypted storage is enabled or not.
     */
    public boolean isEncryptedStorageEnabled() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns the size of encrypted storage (in bytes) available in the VM, or 0 if encrypted
     * storage is not enabled
     */
    @IntRange(from = 0)
    public long getEncryptedStorageBytes() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns whether the app can read the VM console or log output. If not, the VM output is
     * automatically forwarded to the host logcat.
     *
     * @see Builder#setVmOutputCaptured
     */
    public boolean isVmOutputCaptured() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns whether the app can write to the VM console.
     *
     * @see Builder#setVmConsoleInputSupported
     */
    public boolean isVmConsoleInputSupported() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns whether to connect the VM console to a host console.
     *
     * @see Builder#setConnectVmConsole
     */
    public boolean isConnectVmConsole() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns the OS of the VM.
     *
     * @see Builder#setOs
     */
    @NonNull
    @OsName
    public String getOs() {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns whether this VM enabled the hint to use transparent huge pages.
     *
     * <p>This method is currently behind the feature flag
     * "promote_set_should_use_hugepages_to_system_api" and might not exist on all devices.
     *
     * @see Builder#setShouldUseHugepages
     */
    public boolean shouldUseHugepages() {
        throw new RuntimeException("STUB");
    }

    /**
     * Tests if this config is compatible with other config. Being compatible means that the configs
     * can be interchangeably used for the same virtual machine; they do not change the VM identity
     * or secrets. Such changes include varying the number of CPUs or the size of the RAM. Changes
     * that would alter the identity of the VM (e.g. using a different payload or changing the debug
     * mode) are considered incompatible.
     *
     * @see VirtualMachine#setConfig
     */
    public boolean isCompatibleWith(@NonNull VirtualMachineConfig other) {
        throw new RuntimeException("STUB");
    }

    /** A builder used to create a {@link VirtualMachineConfig}. */
    public static final class Builder {

        /** Creates a builder for the given context. */
        public Builder(@NonNull Context context) {
            throw new RuntimeException("STUB");
        }

        /**
         * Creates a builder for a specific package. If packageName is null, {@link #setApkPath}
         * must be called to specify the APK containing the payload.
         */
        public Builder(@Nullable String packageName) {
            throw new RuntimeException("STUB");
        }

        /**
         * Builds an immutable {@link VirtualMachineConfig}
         */
        @NonNull
        public VirtualMachineConfig build() {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets the absolute path of the APK containing the binary payload that will execute within
         * the VM. If not set explicitly, defaults to the split APK containing the payload, if there
         * is one, and otherwise the primary APK of the context.
         */
        @NonNull
        public Builder setApkPath(@NonNull String apkPath) {
            throw new RuntimeException("STUB");
        }

        /**
         * Specify the package name of an extra APK to be included in the VM. Each extra APK is
         * mounted, in unzipped form, inside the VM, allowing access to the code and/or data within
         * it. The VM entry point must be in the main APK.
         */
        @NonNull
        public Builder addExtraApk(@NonNull String packageName) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets the path within the APK to the payload config file that defines software aspects of
         * the VM. The file is a JSON file; see
         * packages/modules/Virtualization/libs/libmicrodroid_payload_metadata/config/src/lib.rs for
         * the format.
         */
        @RequiresPermission(VirtualMachine.USE_CUSTOM_VIRTUAL_MACHINE_PERMISSION)
        @NonNull
        public Builder setPayloadConfigPath(@NonNull String payloadConfigPath) {
            throw new RuntimeException("STUB");
        }

        /** Sets the custom config file to launch the custom VM. */
        @RequiresPermission(VirtualMachine.USE_CUSTOM_VIRTUAL_MACHINE_PERMISSION)
        @NonNull
        public Builder setCustomImageConfig(
                @NonNull VirtualMachineCustomImageConfig customImageConfig) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets the name of the payload binary file that will be executed within the VM, e.g.
         * "payload.so". The file must reside in the {@code lib/<ABI>} directory of the APK.
         *
         * <p>Note that VMs only support 64-bit code, even if the owning app is running as a 32-bit
         * process.
         */
        @NonNull
        public Builder setPayloadBinaryName(@NonNull String payloadBinaryName) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets the debug level. Defaults to {@link #DEBUG_LEVEL_NONE}.
         *
         * <p>If {@link #DEBUG_LEVEL_FULL} is set then logs from inside the VM are exported to the
         * host and adb connections from the host are possible. This is convenient for debugging but
         * may compromise the integrity of the VM - including bypassing the protections offered by a
         * {@linkplain #setProtectedVm protected VM}.
         *
         * <p>Note that it isn't possible to {@linkplain #isCompatibleWith change} the debug level
         * of a VM instance; debug and non-debug VMs always have different secrets.
         */
        @NonNull
        public Builder setDebugLevel(@DebugLevel int debugLevel) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets whether to protect the VM memory from the host. No default is provided, this must be
         * set explicitly.
         *
         * <p>Note that if debugging is {@linkplain #setDebugLevel enabled} for a protected VM, the
         * VM is not truly protected - direct memory access by the host is prevented, but e.g. the
         * debugger can be used to access the VM's internals.
         *
         * <p>It isn't possible to {@linkplain #isCompatibleWith change} the protected status of a
         * VM instance; protected and non-protected VMs always have different secrets.
         *
         * @see VirtualMachineManager#getCapabilities
         */
        @NonNull
        public Builder setProtectedVm(boolean protectedVm) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets the amount of RAM to give the VM, in bytes. If not explicitly set then a default
         * size will be used.
         */
        @NonNull
        public Builder setMemoryBytes(@IntRange(from = 1) long memoryBytes) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets the CPU topology configuration of the VM. Defaults to {@link #CPU_TOPOLOGY_ONE_CPU}.
         *
         * <p>This determines how many virtual CPUs will be created, and their performance and
         * scheduling characteristics, such as affinity masks. Topology also has an effect on memory
         * usage as each vCPU requires additional memory to keep its state.
         */
        @NonNull
        public Builder setCpuTopology(@CpuTopology int cpuTopology) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets the serial device for VM console input.
         *
         * @see android.system.virtualizationservice.ConsoleInputDevice
         *
         * @noinspection JavadocReference
         */
        public Builder setConsoleInputDevice(@Nullable String consoleInputDevice) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets the size (in bytes) of encrypted storage available to the VM. If not set, no
         * encrypted storage is provided.
         *
         * <p>The storage is encrypted with a key deterministically derived from the VM identity
         *
         * <p>The encrypted storage is persistent across VM reboots as well as device reboots. The
         * backing file (containing encrypted data) is stored in the app's private data directory.
         *
         * <p>Note - There is no integrity guarantee or rollback protection on the storage in case
         * the encrypted data is modified.
         *
         * <p>Deleting the VM will delete the encrypted data - there is no way to recover that data.
         */
        @NonNull
        public Builder setEncryptedStorageBytes(@IntRange(from = 1) long encryptedStorageBytes) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets whether to allow the app to read the VM outputs (console / log). Default is {@code
         * false}.
         *
         * <p>By default, console and log outputs of a {@linkplain #setDebugLevel debuggable} VM are
         * automatically forwarded to the host logcat. Setting this as {@code true} will allow the
         * app to directly read {@linkplain VirtualMachine#getConsoleOutput console output} and
         * {@linkplain VirtualMachine#getLogOutput log output}, instead of forwarding them to the
         * host logcat.
         *
         * <p>If you turn on output capture, you must consume data from {@link
         * VirtualMachine#getConsoleOutput} and {@link VirtualMachine#getLogOutput} - because
         * otherwise the code in the VM may get blocked when the pipe buffer fills up.
         *
         * <p>The {@linkplain #setDebugLevel debug level} must be {@link #DEBUG_LEVEL_FULL} to be
         * set as true.
         */
        @NonNull
        public Builder setVmOutputCaptured(boolean captured) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets whether to allow the app to write to the VM console. Default is {@code false}.
         *
         * <p>Setting this as {@code true} will allow the app to directly write into {@linkplain
         * VirtualMachine#getConsoleInput console input}.
         *
         * <p>The {@linkplain #setDebugLevel debug level} must be {@link #DEBUG_LEVEL_FULL} to be
         * set as true.
         */
        @NonNull
        public Builder setVmConsoleInputSupported(boolean supported) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets whether to connect the VM console to a host console. Default is {@code false}.
         *
         * <p>Setting this as {@code true} will allow the shell to directly communicate with the VM
         * console through the connected host console.
         *
         * <p>The {@linkplain #setDebugLevel debug level} must be {@link #DEBUG_LEVEL_FULL} to be
         * set as true.
         */
        @NonNull
        public Builder setConnectVmConsole(boolean supported) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets the path to the disk image with vendor-specific modules.
         */
        @RequiresPermission(VirtualMachine.USE_CUSTOM_VIRTUAL_MACHINE_PERMISSION)
        @NonNull
        public Builder setVendorDiskImage(@NonNull File vendorDiskImage) {
            throw new RuntimeException("STUB");
        }

        /**
         * Sets an OS for the VM. Defaults to {@code "microdroid"}.
         *
         * <p>See {@link VirtualMachineManager#getSupportedOSList} for available OS names.
         */
        @RequiresPermission(VirtualMachine.USE_CUSTOM_VIRTUAL_MACHINE_PERMISSION)
        @NonNull
        public Builder setOs(@NonNull String os) {
            throw new RuntimeException("STUB");
        }

        @NonNull
        public Builder setShouldBoostUclamp(boolean shouldBoostUclamp) {
            throw new RuntimeException("STUB");
        }

        /**
         * Hints whether the VM should make use of the transparent huge pages feature.
         *
         * <p>Note: this API just provides a hint, whether the VM will actually use transparent huge
         * pages additionally depends on the following:
         *
         * <ul>
         *   <li>{@code /sys/kernel/mm/transparent_hugepages/shmem_enabled} should be configured
         *       with the value {@code 'advise'}.
         *   <li>Android host kernel version should be at least {@code android15-5.15}
         * </ul>
         *
         * <p>This method is currently behind the feature flag
         * "promote_set_should_use_hugepages_to_system_api" and might not exist on all devices.
         *
         * @see https://docs.kernel.org/admin-guide/mm/transhuge.html
         * @see
         *     https://cs.android.com/android/platform/superproject/main/+/main:packages/modules/Virtualization/docs/hugepages.md
         *
         * @noinspection JavadocReference
         */
        @NonNull
        public Builder setShouldUseHugepages(boolean shouldUseHugepages) {
            throw new RuntimeException("STUB");
        }
    }
}
