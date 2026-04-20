package app.isfa.kart

import android.content.Context
import app.isfa.kart.db.DataModule
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import app.isfa.kart.repository.RepositoryModule
import app.isfa.kart.repository.api.KartAddCardRepository
import app.isfa.kart.repository.api.KartBrandInfoRepository
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartSearchCardRepository

class DIModuleManager(context: Context) {

    val db = DataModule.providesKartDatabase(context)

    // Membership
    val membershipDao = DataModule.providesKartMembershipDao(db)
    val membershipDataSource = DataModule.providesKartMembershipDataSource(membershipDao)

    // Subscription
    val subscriptionDao = DataModule.providesKartSubscriptionDao(db)
    val subscriptionDataSource = DataModule.providesKartSubscriptionDataSource(subscriptionDao)

    // Brand Info
    val brandInfoDao = DataModule.providesKartBrandInfoDao(db)
    val brandInfoDataSource = DataModule.providesKartBrandInfoDataSource(brandInfoDao)

    fun kartFetchCardRepository(): KartCardRepository {
        return RepositoryModule.providesKartCardRepository(
            membershipDataSource,
            subscriptionDataSource
        )
    }

    fun kartSearchCardRepository(): KartSearchCardRepository {
        return RepositoryModule.providesKartSearchRepository(
            kartFetchCardRepository()
        )
    }

    fun kartAddCardRepository(): KartAddCardRepository {
        return RepositoryModule.providesKartAddCardRepository(
            membershipDataSource,
            subscriptionDataSource
        )
    }

    fun kartBrandInfoRepository(): KartBrandInfoRepository {
        return RepositoryModule.providesKartBrandInfoRepository(
            brandInfoDataSource
        )
    }
}