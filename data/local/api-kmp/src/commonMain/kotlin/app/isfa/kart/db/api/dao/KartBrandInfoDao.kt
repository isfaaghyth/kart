package app.isfa.kart.db.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.entity.KartBrandInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KartBrandInfoDao {

    @Query("SELECT * FROM brand_infos ORDER BY name ASC")
    fun all(): Flow<List<KartBrandInfoEntity>>

    @Query("SELECT * FROM brand_infos WHERE type = :type ORDER BY name ASC")
    fun allByType(type: BrandTypeOf): Flow<List<KartBrandInfoEntity>>

    @Query("SELECT * FROM brand_infos WHERE slug = :slug LIMIT 1")
    suspend fun findBySlug(slug: String): KartBrandInfoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: KartBrandInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(entities: List<KartBrandInfoEntity>)
}