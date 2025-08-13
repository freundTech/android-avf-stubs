plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.com.vanniktech.maven.publish)
}

android {
    namespace = "io.freund.adrian.android.virtualmachine.stubs"
    compileSdk = 36

    defaultConfig {
        minSdk = 34

        externalNativeBuild {
            cmake {
                arguments += listOf(
                    "-DANDROID_STL=none",
                    "-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON",
                )
            }
            ndk {
                abiFilters += listOf("arm64-v8a", "x86_64")
            }
        }
    }

    lint {
        checkAllWarnings = true
        warningsAsErrors = true
        disable += listOf(
            "UnknownNullness",  // Upstream Android source doesn't use strict nullness
            "AndroidGradlePluginVersion",  // We use dependabot instead. Don't want CI to fail because of this
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        prefabPublishing = true
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    prefab {
        create("vm_payload") {
            headers = "src/main/cpp/include"
        }
    }

    packaging {
        jniLibs {
            excludes += "**/libvm_payload.so"
        }
    }
}

dependencies {
    compileOnly(libs.androidx.annotation)
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates("io.freund.adrian.android.virtualmachine", "avf-stubs", "1.1-SNAPSHOT")

    pom {
        name = "Android Virtualization Framework Stubs"
        description = "Library stubs for linking against and using the Android Virtualization Framework"
        url = "https://github.com/freundTech/android-virtualization-framework-stubs/"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "freundTech"
                name = "Adrian Freund"
                email = "adrian@freund.io"
            }
        }
        scm {
            url = "https://github.com/freundTech/android-virtualization-framework-stubs/"
            connection = "scm:git:https://github.com/freundTech/android-virtualization-framework-stubs.git"
            developerConnection = "scm:git:ssh://git@github.com:freundTech/android-virtualization-framework-stubs.git"
        }
    }
}
