package app.isfa.kart.db.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import app.isfa.kart.db.api.entity.KartLastOpenedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KartLastOpenedDao {

    @Query("SELECT * FROM last_opened ORDER BY id DESC")
    fun all(): Flow<List<KartLastOpenedEntity>>

    @Query("SELECT COUNT(*) FROM last_opened")
    suspend fun itemCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: KartLastOpenedEntity): Long

    @Query("DELETE FROM last_opened WHERE id = (SELECT id FROM last_opened ORDER BY id ASC LIMIT 1)")
    suspend fun deleteOldest(): Int

    @Query("DELETE FROM last_opened")
    suspend fun clearAll(): Int

    @Transaction
    suspend fun update(entity: KartLastOpenedEntity) {
        if (itemCount() >= MAX_RECENT_LAST_OPENED) {
            deleteOldest()
        }
        insert(entity)
    }

    companion object {
        private const val MAX_RECENT_LAST_OPENED = 5
    }
}