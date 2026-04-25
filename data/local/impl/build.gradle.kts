plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "app.isfa.kart.db"

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }
}

dependencies {
    implementation(projects.data.local.api)
    implementation(projects.libraries.i18n)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.serialization.core)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}