package app.isfa.kart.db.api.source.brand

import app.isfa.kart.db.api.BrandTypeOf
import kotlinx.coroutines.flow.Flow

interface KartBrandInfoDataSource {

    fun all(): Flow<List<KartBrandModel>>

    fun allByType(type: BrandTypeOf): Flow<List<KartBrandModel>>

    suspend fun findBySlug(slug: String): KartBrandModel?

    suspend fun upsert(brand: KartBrandModel)

    suspend fun upsertAll(brands: List<KartBrandModel>)
}
