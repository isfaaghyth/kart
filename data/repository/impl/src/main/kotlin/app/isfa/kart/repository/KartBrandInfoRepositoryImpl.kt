package app.isfa.kart.repository

import app.isfa.kart.db.api.source.brand.KartBrandInfoDataSource
import app.isfa.kart.db.api.source.brand.KartBrandModel
import app.isfa.kart.repository.api.KartBrandInfoRepository

class KartBrandInfoRepositoryImpl(
    private val brandInfoDataSource: KartBrandInfoDataSource
) : KartBrandInfoRepository {

    override suspend fun addAll(brands: List<KartBrandModel>) {
        brandInfoDataSource.upsertAll(brands)
    }
}