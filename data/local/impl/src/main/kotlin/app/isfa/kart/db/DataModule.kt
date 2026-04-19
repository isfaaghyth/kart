package app.isfa.kart.db

import android.content.Context
import app.isfa.kart.db.api.dao.KartMembershipDao
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource

object DataModule {

    fun providesKartDatabase(appContext: Context) = KartDatabase.create(appContext)

    fun providesKartMembershipDao(db: KartDatabase) = db.membershipDao()

    fun providesKartMembershipDataSource(dao: KartMembershipDao): KartMembershipDataSource =
        KartMembershipDataSourceImpl(dao)
}