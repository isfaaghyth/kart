package app.isfa.kart.repository.api

import app.isfa.kart.db.api.source.brand.KartBrandModel

interface KartBrandInfoRepository {

    suspend fun prefetch()

    suspend fun insert(model: KartBrandModel)
}