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

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringDef;
import androidx.annotation.WorkerThread;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Manages {@linkplain VirtualMachine virtual machine} instances created by an app. Each instance is
 * created from a {@linkplain VirtualMachineConfig configuration} that defines the shape of the VM
 * (RAM, CPUs), the code to execute within it, etc.
 *
 * <p>Each virtual machine instance is named; the configuration and related state of each is
 * persisted in the app's private data directory and an instance can be retrieved given the name.
 * The name must be a valid directory name and must not contain '/'.
 *
 * <p>The app can then start, stop and otherwise interact with the VM.
 *
 * <p>An instance of {@link VirtualMachineManager} can be obtained by calling {@link
 * Context#getSystemService(Class)}.
 */
public class VirtualMachineManager {
    public VirtualMachineManager(@NonNull Context context) {
        throw new RuntimeException("STUB");
    }

    /** Capabilities of the virtual machine implementation. */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef(
        flag = true,
        value = {CAPABILITY_PROTECTED_VM, CAPABILITY_NON_PROTECTED_VM}
    )
    public @interface Capability {}

    /**
     * The implementation supports creating protected VMs, whose memory is inaccessible to the host
     * OS.
     *
     * @see VirtualMachineConfig.Builder#setProtectedVm
     */
    public static final int CAPABILITY_PROTECTED_VM = 1;

    /**
     * The implementation supports creating non-protected VMs, whose memory is accessible to the
     * host OS.
     *
     * @see VirtualMachineConfig.Builder#setProtectedVm
     */
    public static final int CAPABILITY_NON_PROTECTED_VM = 2;

    /** Features provided by {@link VirtualMachineManager}. */
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
        FEATURE_DICE_CHANGES,
        FEATURE_LLPVM_CHANGES,
        FEATURE_MULTI_TENANT,
        FEATURE_NETWORK,
        FEATURE_REMOTE_ATTESTATION,
        FEATURE_VENDOR_MODULES,
    })
    public @interface Features {}

    /** Feature to include new data in the VM DICE chain. */
    public static final String FEATURE_DICE_CHANGES = "com.android.kvm.DICE_CHANGES";

    /** Feature to run payload as non-root user. */
    public static final String FEATURE_MULTI_TENANT = "com.android.kvm.MULTI_TENANT";

    /** Feature to allow network features in VM. */
    public static final String FEATURE_NETWORK = "com.android.kvm.NETWORK";

    /** Feature to allow remote attestation in Microdroid. */
    public static final String FEATURE_REMOTE_ATTESTATION = "com.android.kvm.REMOTE_ATTESTATION";

    /** Feature to allow vendor modules in Microdroid. */
    public static final String FEATURE_VENDOR_MODULES = "com.android.kvm.VENDOR_MODULES";

    /** Feature to enable Secretkeeper protected secrets in Microdroid based pVMs. */
    public static final String FEATURE_LLPVM_CHANGES = "com.android.kvm.LLPVM_CHANGES";

    /**
     * Returns a set of flags indicating what this implementation of virtualization is capable of.
     *
     * @see #CAPABILITY_PROTECTED_VM
     * @see #CAPABILITY_NON_PROTECTED_VM
     */
    @Capability
    public int getCapabilities() {
        throw new RuntimeException("STUB");
    }

    /**
     * Creates a new {@link VirtualMachine} with the given name and config. Creating a virtual
     * machine with the same name as an existing virtual machine is an error. The existing virtual
     * machine has to be deleted before its name can be reused.
     *
     * <p>Each successful call to this method creates a new (and different) virtual machine even if
     * the name and the config are the same as a deleted one. The new virtual machine will initially
     * be stopped.
     *
     * <p>NOTE: This method may block and should not be called on the main thread.
     *
     * @throws VirtualMachineException if the VM cannot be created, or there is an existing VM with
     *     the given name.
     */
    @NonNull
    @WorkerThread
    @RequiresPermission(VirtualMachine.MANAGE_VIRTUAL_MACHINE_PERMISSION)
    public VirtualMachine create(@NonNull String name, @NonNull VirtualMachineConfig config)
            throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns an existing {@link VirtualMachine} with the given name. Returns null if there is no
     * such virtual machine.
     *
     * <p>There is at most one {@code VirtualMachine} object corresponding to a given virtual
     * machine instance. Multiple calls to get() passing the same name will get the same object
     * returned, until the virtual machine is deleted (via {@link #delete}) and then recreated.
     *
     * <p>NOTE: This method may block and should not be called on the main thread.
     *
     * @see #getOrCreate
     * @throws VirtualMachineException if the virtual machine exists but could not be successfully
     *     retrieved. This can be resolved by calling {@link #delete} on the VM.
     */
    @WorkerThread
    @Nullable
    public VirtualMachine get(@NonNull String name) throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }

    /**
     * Imports a virtual machine from an {@link VirtualMachineDescriptor} object and associates it
     * with the given name.
     *
     * <p>The new virtual machine will be in the same state as the descriptor indicates. The
     * descriptor is automatically closed and cannot be used again.
     *
     * <p>NOTE: This method may block and should not be called on the main thread.
     *
     * @throws VirtualMachineException if the VM cannot be imported or the {@code
     *     VirtualMachineDescriptor} has already been closed.
     */
    @NonNull
    @WorkerThread
    public VirtualMachine importFromDescriptor(@NonNull String name, @NonNull VirtualMachineDescriptor vmDescriptor)
            throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns an existing {@link VirtualMachine} if it exists, or create a new one. The config
     * parameter is used only when a new virtual machine is created.
     *
     * <p>NOTE: This method may block and should not be called on the main thread.
     *
     * @throws VirtualMachineException if the virtual machine could not be created or retrieved.
     */
    @WorkerThread
    @NonNull
    public VirtualMachine getOrCreate(@NonNull String name, @NonNull VirtualMachineConfig config)
            throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }

    /**
     * Deletes an existing {@link VirtualMachine}. Deleting a virtual machine means deleting any
     * persisted data associated with it including the per-VM secret. This is an irreversible
     * action. A virtual machine once deleted can never be restored. A new virtual machine created
     * with the same name is different from an already deleted virtual machine even if it has the
     * same config.
     *
     * <p>NOTE: This method may block and should not be called on the main thread.
     *
     * @throws VirtualMachineException if the virtual machine does not exist, is not stopped, or
     *     cannot be deleted.
     */
    @WorkerThread
    public void delete(@NonNull String name) throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }

    /** Returns a list of supported OS names. */
    @NonNull
    public List<String> getSupportedOSList() throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }

    /** Returns {@code true} if given {@code featureName} is enabled. */
    @RequiresPermission(VirtualMachine.MANAGE_VIRTUAL_MACHINE_PERMISSION)
    public boolean isFeatureEnabled(@Features String featureName) throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns {@code true} if the pVM remote attestation feature is supported. Remote attestation
     * allows a protected VM to attest its authenticity to a remote server.
     */
    @RequiresPermission(VirtualMachine.MANAGE_VIRTUAL_MACHINE_PERMISSION)
    public boolean isRemoteAttestationSupported() throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }

    /**
     * Returns {@code true} if Updatable VM feature is supported by AVF. Updatable VM allow secrets
     * and data to be accessible even after updates of boot images and apks. For more info see
     * packages/modules/Virtualization/docs/updatable_vm.md
     */
    @RequiresPermission(VirtualMachine.MANAGE_VIRTUAL_MACHINE_PERMISSION)
    public boolean isUpdatableVmSupported() throws VirtualMachineException {
        throw new RuntimeException("STUB");
    }
}
