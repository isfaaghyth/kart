package app.isfa.kart

import android.app.Application
import app.isfa.kart.repository.api.KartAddCardRepository
import app.isfa.kart.repository.api.KartBrandInfoRepository
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartSearchCardRepository
import com.isfa.kart.home.di.HomeDependencies

class KartApp : Application(), HomeDependencies {

    private val di by lazy { DIModuleManager(applicationContext) }

    override fun kartFetchCardRepository(): KartCardRepository {
        return di.kartFetchCardRepository()
    }

    override fun kartSearchCardRepository(): KartSearchCardRepository {
        return di.kartSearchCardRepository()
    }

    override fun kartAddCardRepository(): KartAddCardRepository {
        return di.kartAddCardRepository()
    }

    override fun kartBrandInfoRepository(): KartBrandInfoRepository {
        return di.kartBrandInfoRepository()
    }
}