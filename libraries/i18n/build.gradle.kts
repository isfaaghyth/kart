plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "app.isfa.kart.i18n"

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }
}
