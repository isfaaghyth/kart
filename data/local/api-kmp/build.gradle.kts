plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {
    androidLibrary {
        namespace = "app.isfa.kart.db.api"
        compileSdk {
            version = release(36) { minorApiLevel = 1 }
        }
        minSdk = 24
    }

    val xcfName = "DbApi"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)

                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.coroutines)

                implementation(libs.androidx.room.kmp.common)
            }
        }
    }
}
