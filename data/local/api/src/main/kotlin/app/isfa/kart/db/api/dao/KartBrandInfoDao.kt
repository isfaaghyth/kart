package app.isfa.kart.db.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.isfa.kart.db.api.entity.KartBrandInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KartBrandInfoDao {

    @Query("SELECT * FROM brand_infos")
    fun all(): Flow<List<KartBrandInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: KartBrandInfoEntity)
}