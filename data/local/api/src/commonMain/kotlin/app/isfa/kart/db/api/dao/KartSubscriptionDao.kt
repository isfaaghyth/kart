package app.isfa.kart.db.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import app.isfa.kart.db.api.entity.KartSubscriptionEntity
import app.isfa.kart.db.api.entity.KartSubscriptionWithBrand
import kotlinx.coroutines.flow.Flow

@Dao
interface KartSubscriptionDao {

    @Transaction
    @Query("SELECT * FROM kart_subscriptions ORDER BY id DESC")
    fun all(): Flow<List<KartSubscriptionWithBrand>>

    @Insert
    suspend fun insert(entity: KartSubscriptionEntity): Long

    @Update
    suspend fun update(entity: KartSubscriptionEntity): Int

    @Query("DELETE FROM kart_subscriptions WHERE id = :subscriptionId")
    suspend fun delete(subscriptionId: Int): Int
}