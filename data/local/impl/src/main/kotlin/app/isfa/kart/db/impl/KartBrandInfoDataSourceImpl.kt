package app.isfa.kart.db.impl

import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.dao.KartBrandInfoDao
import app.isfa.kart.db.api.entity.KartBrandInfoEntity
import app.isfa.kart.db.api.source.brand.KartBrandInfoDataSource
import app.isfa.kart.db.api.source.brand.KartBrandModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KartBrandInfoDataSourceImpl(private val dao: KartBrandInfoDao) : KartBrandInfoDataSource {

    override fun all(): Flow<List<KartBrandModel>> {
        return dao.all().map { list -> list.map { it.toModel() } }
    }

    override fun allByType(type: BrandTypeOf): Flow<List<KartBrandModel>> {
        return dao.allByType(type).map { list -> list.map { it.toModel() } }
    }

    override suspend fun findBySlug(slug: String): KartBrandModel? {
        return dao.findBySlug(slug)?.toModel()
    }

    override suspend fun upsertAll(brands: List<KartBrandModel>) {
        dao.upsertAll(brands.map { it.toEntity() })
    }

    private fun KartBrandInfoEntity.toModel() = KartBrandModel(
        slug = slug,
        name = name,
        type = type,
        faviconUrl = faviconUrl,
        url = url,
        colors = colors,
    )

    private fun KartBrandModel.toEntity() = KartBrandInfoEntity(
        slug = slug,
        name = name,
        type = type,
        faviconUrl = faviconUrl,
        url = url,
        colors = colors,
    )
}
