plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "app.isfa.kart.repository.api"

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }
}

dependencies {
    // Brings in KartBrandModel, KartMembershipModel, KartSubscriptionModel,
    // BrandTypeOf, MerchantCategory, CardType, SubscriptionType, and the
    // two data-source interfaces the repository depends on.
    api(project(":data:local:api-kmp"))
    implementation(libs.kotlinx.coroutines)
}
