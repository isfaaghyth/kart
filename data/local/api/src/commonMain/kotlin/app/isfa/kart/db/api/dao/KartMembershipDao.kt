package app.isfa.kart.db.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import app.isfa.kart.db.api.entity.KartMemberWithBrand
import app.isfa.kart.db.api.entity.KartMembershipEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KartMembershipDao {

    @Transaction
    @Query("SELECT * FROM kart_members ORDER BY id DESC")
    fun all(): Flow<List<KartMemberWithBrand>>

    @Insert
    suspend fun insert(entity: KartMembershipEntity): Long

    @Update
    suspend fun update(entity: KartMembershipEntity): Int

    @Query("DELETE FROM kart_members WHERE id = :cardId")
    suspend fun delete(cardId: Int): Int
}