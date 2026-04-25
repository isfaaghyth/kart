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

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
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
