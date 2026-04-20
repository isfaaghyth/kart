package app.isfa.kart.db.impl

import app.isfa.kart.db.api.dao.KartMembershipDao
import app.isfa.kart.db.api.entity.KartMembershipEntity
import app.isfa.kart.db.api.source.brand.KartBrandModel
import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import app.isfa.kart.db.api.source.membership.KartMembershipModel
import com.isfa.kart.str.StringProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class KartMembershipDataSourceImpl(
    private val dao: KartMembershipDao,
    private val stringProvider: StringProvider
) : KartMembershipDataSource {

    override fun members(): Flow<List<KartMembershipModel>> {
        return dao.all().map { list ->
            list.map { joined ->
                KartMembershipModel(
                    id = joined.member.id,
                    accountId = joined.member.accountId,
                    cardType = joined.member.cardType,
                    brand = KartBrandModel(
                        slug = joined.brand.slug,
                        name = joined.brand.name,
                        type = joined.brand.type,
                        category = joined.brand.category,
                        faviconUrl = joined.brand.faviconUrl,
                        url = joined.brand.url,
                        colors = joined.brand.colors,
                    ),
                )
            }
        }
    }

    override suspend fun insert(model: CreateKartMembershipModel): Result<Boolean> {
        if (members().first().any { it.accountId == model.accountId }) {
            return Result.failure(Throwable(stringProvider.duplicateAccountId()))
        }

        val insertion = dao.insert(
            KartMembershipEntity(
                accountId = model.accountId,
                cardType = model.cardType,
                brandSlug = model.brandSlug,
            )
        )

        return if (insertion > 0) {
            Result.success(true)
        } else {
            Result.failure(Throwable(stringProvider.unexpectedError()))
        }
    }

    override suspend fun update(model: CreateKartMembershipModel) {
        dao.update(
            KartMembershipEntity(
                accountId = model.accountId,
                cardType = model.cardType,
                brandSlug = model.brandSlug,
            )
        )
    }

    override suspend fun delete(cardId: Int) {
        dao.delete(cardId)
    }
}