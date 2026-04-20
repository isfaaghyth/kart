package app.isfa.kart.db

import android.content.Context
import app.isfa.kart.db.api.dao.KartBrandInfoDao
import app.isfa.kart.db.api.dao.KartMembershipDao
import app.isfa.kart.db.api.dao.KartSubscriptionDao
import app.isfa.kart.db.api.source.brand.KartBrandInfoDataSource
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import app.isfa.kart.db.api.source.subscription.KartSubscriptionDataSource
import app.isfa.kart.db.impl.KartBrandInfoDataSourceImpl
import app.isfa.kart.db.impl.KartMembershipDataSourceImpl
import app.isfa.kart.db.impl.KartSubscriptionDataSourceImpl
import com.isfa.kart.str.StringProvider

object DataModule {

    fun providesKartDatabase(appContext: Context) = KartDatabase.create(appContext)

    fun providesKartBrandInfoDao(db: KartDatabase): KartBrandInfoDao = db.brandInfoDao()

    fun providesKartMembershipDao(db: KartDatabase): KartMembershipDao = db.membershipDao()

    fun providesKartSubscriptionDao(db: KartDatabase): KartSubscriptionDao = db.subscriptionDao()

    fun providesKartBrandInfoDataSource(dao: KartBrandInfoDao): KartBrandInfoDataSource =
        KartBrandInfoDataSourceImpl(dao)

    fun providesKartMembershipDataSource(
        dao: KartMembershipDao,
        stringProvider: StringProvider
    ): KartMembershipDataSource = KartMembershipDataSourceImpl(dao, stringProvider)

    fun providesKartSubscriptionDataSource(
        dao: KartSubscriptionDao,
        stringProvider: StringProvider
    ): KartSubscriptionDataSource = KartSubscriptionDataSourceImpl(dao, stringProvider)
}