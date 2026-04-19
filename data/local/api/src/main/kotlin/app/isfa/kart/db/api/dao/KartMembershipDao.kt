package app.isfa.kart.db.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import app.isfa.kart.db.api.entity.KartMembershipEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KartMembershipDao {

    @Query("SELECT * FROM kart_members ORDER BY id DESC")
    fun all(): Flow<List<KartMembershipEntity>>

    @Insert
    suspend fun insert(entity: KartMembershipEntity): Long

    @Update
    suspend fun update(entity: KartMembershipEntity): Int

    @Query("DELETE FROM kart_members WHERE id = :cardId")
    suspend fun delete(cardId: Int): Int
}