package app.isfa.kart.db

import app.isfa.kart.db.api.dao.KartMembershipDao
import app.isfa.kart.db.api.entity.KartMembershipEntity
import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import app.isfa.kart.db.api.source.membership.KartMembershipModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KartMembershipDataSourceImpl(private val dao: KartMembershipDao) : KartMembershipDataSource {

    override fun members(): Flow<List<KartMembershipModel>> {
        return dao.all().map {
            it.map { entity ->
                KartMembershipModel(
                    id = entity.id,
                    accountId = entity.accountId,
                    cardType = entity.cardType,
                    merchantName = entity.merchantName,
                    merchantCategory = entity.merchantCategory,
                )
            }
        }
    }

    override suspend fun insert(model: CreateKartMembershipModel) {
        dao.insert(
            KartMembershipEntity(
                accountId = model.accountId,
                cardType = model.cardType,
                merchantName = model.merchantName,
                merchantCategory = model.merchantCategory
            )
        )
    }

    override suspend fun update(model: CreateKartMembershipModel) {
        dao.update(
            KartMembershipEntity(
                accountId = model.accountId,
                cardType = model.cardType,
                merchantName = model.merchantName,
                merchantCategory = model.merchantCategory
            )
        )
    }

    override suspend fun delete(cardId: Int) {
        dao.delete(cardId)
    }
}