package app.isfa.kart.repository.api

import app.isfa.kart.db.api.source.brand.KartBrandModel
import kotlinx.coroutines.flow.Flow

interface KartBrandInfoRepository {

    fun brands(): Flow<List<KartBrandModel>>

    suspend fun prefetch()

    suspend fun insert(model: KartBrandModel)
}