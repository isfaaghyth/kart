package app.isfa.kart.repository

import app.isfa.kart.db.api.source.brand.KartBrandInfoDataSource
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import app.isfa.kart.db.api.source.subscription.KartSubscriptionDataSource
import app.isfa.kart.repository.api.KartAddCardRepository
import app.isfa.kart.repository.api.KartBrandInfoRepository
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartSearchCardRepository
import app.isfa.kart.repository.api.JsonAssetReader

object RepositoryModule {

    fun providesKartCardRepository(
        membershipDataSource: KartMembershipDataSource,
        subscriptionDataSource: KartSubscriptionDataSource,
    ): KartCardRepository = KartCardRepositoryImpl(membershipDataSource, subscriptionDataSource)

    fun providesKartSearchRepository(
        fetchRepository: KartCardRepository
    ): KartSearchCardRepository = KartSearchCardRepositoryImpl(fetchRepository)

    fun providesKartAddCardRepository(
        membershipDataSource: KartMembershipDataSource,
        subscriptionDataSource: KartSubscriptionDataSource,
    ): KartAddCardRepository = KartAddCardRepositoryImpl(membershipDataSource, subscriptionDataSource)

    fun providesKartBrandInfoRepository(
        brandInfoDataSource: KartBrandInfoDataSource,
        assetReader: JsonAssetReader
    ): KartBrandInfoRepository = KartBrandInfoRepositoryImpl(brandInfoDataSource, assetReader)
}
