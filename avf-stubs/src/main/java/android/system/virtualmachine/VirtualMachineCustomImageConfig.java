/*
 * Copyright (C) 2024 The Android Open Source Project
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

import androidx.annotation.Nullable;

import java.util.List;

public class VirtualMachineCustomImageConfig {
    @Nullable
    public Disk[] getDisks() {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public String getBootloaderPath() {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public String getInitrdPath() {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public String getKernelPath() {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public String getName() {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public String[] getParams() {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public SharedPath[] getSharedPaths() {
        throw new RuntimeException("STUB");
    }

    public boolean useTouch() {
        throw new RuntimeException("STUB");
    }

    public boolean useKeyboard() {
        throw new RuntimeException("STUB");
    }

    public boolean useMouse() {
        throw new RuntimeException("STUB");
    }

    public boolean useSwitches() {
        throw new RuntimeException("STUB");
    }

    public boolean useTrackpad() {
        throw new RuntimeException("STUB");
    }

    public boolean useAutoMemoryBalloon() {
        throw new RuntimeException("STUB");
    }

    public boolean useNetwork() {
        throw new RuntimeException("STUB");
    }

    public VirtualMachineCustomImageConfig(
            String name,
            String kernelPath,
            String initrdPath,
            String bootloaderPath,
            String[] params,
            Disk[] disks,
            SharedPath[] sharedPaths,
            DisplayConfig displayConfig,
            boolean touch,
            boolean keyboard,
            boolean mouse,
            boolean switches,
            boolean network,
            GpuConfig gpuConfig,
            AudioConfig audioConfig,
            boolean trackpad,
            boolean autoMemoryBalloon,
            UsbConfig usbConfig) {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public AudioConfig getAudioConfig() {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public DisplayConfig getDisplayConfig() {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public GpuConfig getGpuConfig() {
        throw new RuntimeException("STUB");
    }

    @Nullable
    public UsbConfig getUsbConfig() {
        throw new RuntimeException("STUB");
    }

    public static final class SharedPath {
        public SharedPath(
                String path,
                int hostUid,
                int hostGid,
                int guestUid,
                int guestGid,
                int mask,
                String tag,
                String socket,
                boolean appDomain,
                String socketPath) {
            throw new RuntimeException("STUB");
        }

        public String getSharedPath() {
            throw new RuntimeException("STUB");
        }

        public int getHostUid() {
            throw new RuntimeException("STUB");
        }

        public int getHostGid() {
            throw new RuntimeException("STUB");
        }

        public int getGuestUid() {
            throw new RuntimeException("STUB");
        }

        public int getGuestGid() {
            throw new RuntimeException("STUB");
        }

        public int getMask() {
            throw new RuntimeException("STUB");
        }

        public String getTag() {
            throw new RuntimeException("STUB");
        }

        public String getSocket() {
            throw new RuntimeException("STUB");
        }

        public boolean getAppDomain() {
            throw new RuntimeException("STUB");
        }

        public String getSocketPath() {
            throw new RuntimeException("STUB");
        }
    }

    public static final class Disk {
        public static Disk RWDisk(String imagePath) {
            throw new RuntimeException("STUB");
        }

        public static Disk RODisk(String imagePath) {
            throw new RuntimeException("STUB");
        }

        public boolean isWritable() {
            throw new RuntimeException("STUB");
        }

        public String getImagePath() {
            throw new RuntimeException("STUB");
        }

        public Disk addPartition(Partition p) {
            throw new RuntimeException("STUB");
        }

        public List<Partition> getPartitions() {
            throw new RuntimeException("STUB");
        }
    }

    public static final class Partition {
        public final String name;
        public final String imagePath;
        public final boolean writable;
        public final String guid;

        public Partition(String name, String imagePath, boolean writable, String guid) {
            throw new RuntimeException("STUB");
        }
    }

    public static final class Builder {
        public Builder() {}

        public Builder setName(String name) {
            throw new RuntimeException("STUB");
        }

        public Builder setKernelPath(String kernelPath) {
            throw new RuntimeException("STUB");
        }

        public Builder setBootloaderPath(String bootloaderPath) {
            throw new RuntimeException("STUB");
        }

        public Builder setInitrdPath(String initrdPath) {
            throw new RuntimeException("STUB");
        }

        public Builder addDisk(Disk disk) {
            throw new RuntimeException("STUB");
        }

        public Builder addSharedPath(SharedPath path) {
            throw new RuntimeException("STUB");
        }

        public Builder addParam(String param) {
            throw new RuntimeException("STUB");
        }

        public Builder setDisplayConfig(DisplayConfig displayConfig) {
            throw new RuntimeException("STUB");
        }

        public Builder setGpuConfig(GpuConfig gpuConfig) {
            throw new RuntimeException("STUB");
        }

        public Builder useTouch(boolean touch) {
            throw new RuntimeException("STUB");
        }

        public Builder useKeyboard(boolean keyboard) {
            throw new RuntimeException("STUB");
        }

        public Builder useMouse(boolean mouse) {
            throw new RuntimeException("STUB");
        }

        public Builder useSwitches(boolean switches) {
            throw new RuntimeException("STUB");
        }

        public Builder useTrackpad(boolean trackpad) {
            throw new RuntimeException("STUB");
        }

        public Builder useAutoMemoryBalloon(boolean autoMemoryBalloon) {
            throw new RuntimeException("STUB");
        }

        public Builder useNetwork(boolean network) {
            throw new RuntimeException("STUB");
        }

        public Builder setAudioConfig(AudioConfig audioConfig) {
            throw new RuntimeException("STUB");
        }

        public Builder setUsbConfig(UsbConfig usbConfig) {
            throw new RuntimeException("STUB");
        }

        public VirtualMachineCustomImageConfig build() {
            throw new RuntimeException("STUB");
        }
    }

    public static final class UsbConfig {
        public final boolean controller;

        public UsbConfig(boolean controller) {
            throw new RuntimeException("STUB");
        }

        public boolean getUsbController() {
            throw new RuntimeException("STUB");
        }

        public static class Builder {
            public Builder() {}

            public Builder setController(boolean useController) {
                throw new RuntimeException("STUB");
            }

            public UsbConfig build() {
                throw new RuntimeException("STUB");
            }
        }
    }

    public static final class AudioConfig {
        public boolean useMicrophone() {
            throw new RuntimeException("STUB");
        }

        public boolean useSpeaker() {
            throw new RuntimeException("STUB");
        }

        public static class Builder {
            public Builder() {}

            public Builder setUseMicrophone(boolean useMicrophone) {
                throw new RuntimeException("STUB");
            }

            public Builder setUseSpeaker(boolean useSpeaker) {
                throw new RuntimeException("STUB");
            }

            public AudioConfig build() {
                throw new RuntimeException("STUB");
            }
        }
    }

    public static final class DisplayConfig {
        public int getWidth() {
            throw new RuntimeException("STUB");
        }

        public int getHeight() {
            throw new RuntimeException("STUB");
        }

        public int getHorizontalDpi() {
            throw new RuntimeException("STUB");
        }

        public int getVerticalDpi() {
            throw new RuntimeException("STUB");
        }

        public int getRefreshRate() {
            throw new RuntimeException("STUB");
        }

        public static class Builder {
            public Builder() {}

            public Builder setWidth(int width) {
                throw new RuntimeException("STUB");
            }

            public Builder setHeight(int height) {
                throw new RuntimeException("STUB");
            }

            public Builder setHorizontalDpi(int horizontalDpi) {
                throw new RuntimeException("STUB");
            }

            public Builder setVerticalDpi(int verticalDpi) {
                throw new RuntimeException("STUB");
            }

            public Builder setRefreshRate(int refreshRate) {
                throw new RuntimeException("STUB");
            }

            public DisplayConfig build() {
                throw new RuntimeException("STUB");
            }
        }
    }

    public static final class GpuConfig {
        public String getBackend() {
            throw new RuntimeException("STUB");
        }

        public String[] getContextTypes() {
            throw new RuntimeException("STUB");
        }

        public String getPciAddress() {
            throw new RuntimeException("STUB");
        }

        public String getRendererFeatures() {
            throw new RuntimeException("STUB");
        }

        public boolean getRendererUseEgl() {
            throw new RuntimeException("STUB");
        }

        public boolean getRendererUseGles() {
            throw new RuntimeException("STUB");
        }

        public boolean getRendererUseGlx() {
            throw new RuntimeException("STUB");
        }

        public boolean getRendererUseSurfaceless() {
            throw new RuntimeException("STUB");
        }

        public boolean getRendererUseVulkan() {
            throw new RuntimeException("STUB");
        }

        public static class Builder {
            public Builder() {}

            public Builder setBackend(String backend) {
                throw new RuntimeException("STUB");
            }

            public Builder setContextTypes(String[] contextTypes) {
                throw new RuntimeException("STUB");
            }

            public Builder setPciAddress(String pciAddress) {
                throw new RuntimeException("STUB");
            }

            public Builder setRendererFeatures(String rendererFeatures) {
                throw new RuntimeException("STUB");
            }

            public Builder setRendererUseEgl(Boolean rendererUseEgl) {
                throw new RuntimeException("STUB");
            }

            public Builder setRendererUseGles(Boolean rendererUseGles) {
                throw new RuntimeException("STUB");
            }

            public Builder setRendererUseGlx(Boolean rendererUseGlx) {
                throw new RuntimeException("STUB");
            }

            public Builder setRendererUseSurfaceless(Boolean rendererUseSurfaceless) {
                throw new RuntimeException("STUB");
            }

            public Builder setRendererUseVulkan(Boolean rendererUseVulkan) {
                throw new RuntimeException("STUB");
            }

            public GpuConfig build() {
                throw new RuntimeException("STUB");
            }
        }
    }
}
