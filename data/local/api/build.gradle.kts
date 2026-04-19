plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "app.isfa.kart.db.api"

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.androidx.room.common)
    implementation(libs.kotlinx.coroutines)
}
