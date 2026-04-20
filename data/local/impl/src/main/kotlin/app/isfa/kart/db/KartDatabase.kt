package app.isfa.kart.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.isfa.kart.db.api.converter.BrandTypeConverters
import app.isfa.kart.db.api.converter.CardTypeConverters
import app.isfa.kart.db.api.converter.MerchantCategoryConverters
import app.isfa.kart.db.api.converter.SubscriptionTypeConverters
import app.isfa.kart.db.api.dao.KartBrandInfoDao
import app.isfa.kart.db.api.dao.KartLastOpenedDao
import app.isfa.kart.db.api.dao.KartMembershipDao
import app.isfa.kart.db.api.dao.KartSubscriptionDao
import app.isfa.kart.db.api.entity.KartBrandInfoEntity
import app.isfa.kart.db.api.entity.KartLastOpenedEntity
import app.isfa.kart.db.api.entity.KartMembershipEntity
import app.isfa.kart.db.api.entity.KartSubscriptionEntity
import kotlinx.coroutines.Dispatchers

@Database(
    entities = [
        KartBrandInfoEntity::class,
        KartMembershipEntity::class,
        KartSubscriptionEntity::class,
//        KartLastOpenedEntity::class,
    ],
    version = KartDatabase.VERSION
)
@TypeConverters(
    CardTypeConverters::class,
    SubscriptionTypeConverters::class,
    MerchantCategoryConverters::class,
    BrandTypeConverters::class,
)
abstract class KartDatabase : RoomDatabase() {

    abstract fun brandInfoDao(): KartBrandInfoDao
    abstract fun membershipDao(): KartMembershipDao
    abstract fun subscriptionDao(): KartSubscriptionDao
//    abstract fun lastOpenedDao(): KartLastOpenedDao

    companion object {
        internal const val VERSION = 1
        private const val NAME = "kart_app"

        fun create(appContext: Context): KartDatabase {
            return Room.databaseBuilder(appContext, KartDatabase::class.java, NAME)
                .fallbackToDestructiveMigration(true)
                .fallbackToDestructiveMigrationOnDowngrade(true)
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }
}
