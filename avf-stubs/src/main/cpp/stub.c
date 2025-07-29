#include <android/log.h>
#include <stdlib.h>

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

#define TAG "libvm_payload_stub"

void log_err_and_exit() {
    __android_log_write(
            ANDROID_LOG_ERROR,
            TAG,
            "Stub method called. Make sure not to package the libvm_payload stub in you APK");
    exit(1);
}

void AVmPayload_notifyPayloadReady() {
    log_err_and_exit();
}
void AVmPayload_runVsockRpcServer() {
    log_err_and_exit();
}
void AVmPayload_getVmInstanceSecret() {
    log_err_and_exit();
}
void AVmPayload_getDiceAttestationChain() {
    log_err_and_exit();
}
void AVmPayload_getDiceAttestationCdi() {
    log_err_and_exit();
}
void AVmPayload_getApkContentsPath() {
    log_err_and_exit();
}
void AVmPayload_getEncryptedStoragePath() {
    log_err_and_exit();
}
void AVmPayload_requestAttestation() {
    log_err_and_exit();
}
void AVmPayload_requestAttestationForTesting() {
    log_err_and_exit();
}
void AVmAttestationResult_getPrivateKey() {
    log_err_and_exit();
}
void AVmAttestationResult_sign() {
    log_err_and_exit();
}
void AVmAttestationResult_free() {
    log_err_and_exit();
}
void AVmAttestationStatus_toString() {
    log_err_and_exit();
}
void AVmAttestationResult_getCertificateCount() {
    log_err_and_exit();
}
void AVmAttestationResult_getCertificateAt() {
    log_err_and_exit();
}
void AVmPayload_writeRollbackProtectedSecret() {
    log_err_and_exit();
}
void AVmPayload_readRollbackProtectedSecret() {
    log_err_and_exit();
}
void AVmPayload_isNewInstance() {
    log_err_and_exit();
}
