plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "app.isfa.kart.repository"

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }
}

dependencies {
    implementation(projects.data.repository.api)
    implementation(projects.data.local.impl)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
