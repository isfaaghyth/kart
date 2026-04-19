package app.isfa.kart

import android.app.Application
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import com.isfa.kart.home.di.HomeDependencies

class KartApp : Application(), HomeDependencies {

    override fun membershipDataSource(): KartMembershipDataSource {
        return DIModuleManager.kartMembershipDataSource(applicationContext)
    }
}