package app.isfa.kart

import android.content.Context
import app.isfa.kart.db.DataModule
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource

object DIModuleManager {

    fun kartMembershipDataSource(context: Context): KartMembershipDataSource {
        val db = DataModule.providesKartDatabase(context)
        val dao = DataModule.providesKartMembershipDao(db)
        return DataModule.providesKartMembershipDataSource(dao)
    }
}