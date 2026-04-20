package app.isfa.kart

import android.content.Context
import app.isfa.kart.db.DataModule
import app.isfa.kart.repository.RepositoryModule
import app.isfa.kart.repository.api.KartAddCardRepository
import app.isfa.kart.repository.api.KartBrandInfoRepository
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartSearchCardRepository
import com.isfa.kart.str.impl.GlobalStringProvider
import com.isfa.kart.str.impl.KartStringProvider

class DIModuleManager(context: Context) {

    private val db = DataModule.providesKartDatabase(context)
    private val stringProvider = GlobalStringProvider(
        KartStringProvider(context)
    )

    // Membership
    private val membershipDao = DataModule.providesKartMembershipDao(db)
    private val membershipDataSource = DataModule.providesKartMembershipDataSource(
        dao = membershipDao,
        stringProvider = stringProvider
    )

    // Subscription
    private val subscriptionDao = DataModule.providesKartSubscriptionDao(db)
    private val subscriptionDataSource = DataModule.providesKartSubscriptionDataSource(
        dao = subscriptionDao,
        stringProvider = stringProvider
    )

    // Brand Info
    private val brandInfoDao = DataModule.providesKartBrandInfoDao(db)
    private val brandInfoDataSource = DataModule.providesKartBrandInfoDataSource(brandInfoDao)

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