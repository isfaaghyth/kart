plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "app.isfa.kart.navigation"

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    api(libs.androidx.jetbrains.navigation3.ui)
    api(libs.androidx.jetbrains.navigation3.material3.adaptive)
    api(libs.jetbrains.lifecycle.viewmodelNavigation3)
    api(libs.kotlinx.serialization.core)
}