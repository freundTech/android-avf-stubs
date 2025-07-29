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

/** Holds initialization code for virtualization module */
public class VirtualizationFrameworkInitializer {
    /**
     * Called by the static initializer in the {@link SystemServiceRegistry}, and registers {@link
     * VirtualMachineManager} to the {@link Context}. so that it's accessible from {@link
     * Context#getSystemService(String)}.
     *
     * @noinspection JavadocReference
     */
    public static void registerServiceWrappers() {
        throw new RuntimeException("STUB");
    }
}
