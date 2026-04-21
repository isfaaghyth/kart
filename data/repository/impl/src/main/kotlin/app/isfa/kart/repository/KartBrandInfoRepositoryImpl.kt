package app.isfa.kart.repository

import app.isfa.kart.db.api.source.brand.KartBrandInfoDataSource
import app.isfa.kart.db.api.source.brand.KartBrandModel
import app.isfa.kart.repository.api.KartBrandInfoRepository
import app.isfa.kart.repository.api.JsonAssetReader
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

class KartBrandInfoRepositoryImpl(
    private val brandInfoDataSource: KartBrandInfoDataSource,
    private val assetReader: JsonAssetReader
) : KartBrandInfoRepository {

    override suspend fun prefetch() {
        if (brandInfoDataSource.all().first().isNotEmpty()) return

        val jsonString = assetReader.read(R.raw.brand_list)
        val brands = Json.decodeFromString<List<KartBrandModel>>(jsonString)

        brandInfoDataSource.upsertAll(brands)
    }

    override suspend fun insert(model: KartBrandModel) {
        brandInfoDataSource.upsert(model)
    }
}