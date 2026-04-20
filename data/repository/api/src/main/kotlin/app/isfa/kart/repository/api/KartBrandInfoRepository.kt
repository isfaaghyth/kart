package app.isfa.kart.repository.api

import app.isfa.kart.db.api.source.brand.KartBrandModel

interface KartBrandInfoRepository {

    suspend fun addAll(brands: List<KartBrandModel>)
}