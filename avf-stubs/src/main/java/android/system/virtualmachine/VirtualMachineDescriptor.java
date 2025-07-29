/*
 * Copyright (C) 2022 The Android Open Source Project
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

import static android.system.virtualmachine.StubUtils.stub;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * A VM descriptor that captures the state of a Virtual Machine.
 *
 * <p>You can capture the current state of VM by creating an instance of this class with {@link
 * VirtualMachine#toDescriptor}, optionally pass it to another App, and then build an identical VM
 * with the descriptor received.
 */
public final class VirtualMachineDescriptor implements Parcelable, AutoCloseable {
    @Override
    public int describeContents() {
        throw new RuntimeException("STUB");
    }

    @Override
    public void writeToParcel(@NonNull Parcel out, int flags) {
        throw new RuntimeException("STUB");
    }

    @NonNull
    public static final Creator<VirtualMachineDescriptor> CREATOR = stub();

    /**
     * Release any resources held by this descriptor. Calling {@code close} on an already-closed
     * descriptor has no effect.
     */
    @Override
    public void close() {
        throw new RuntimeException("STUB");
    }
}
