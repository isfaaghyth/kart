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
    implementation(project(":data:repository:api"))
    implementation(project(":data:local:impl"))
    implementation(libs.kotlinx.coroutines)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
