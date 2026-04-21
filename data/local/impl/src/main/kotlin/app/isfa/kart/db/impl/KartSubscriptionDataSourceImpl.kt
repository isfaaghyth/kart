package app.isfa.kart.db.impl

import app.isfa.kart.db.api.dao.KartSubscriptionDao
import app.isfa.kart.db.api.entity.KartSubscriptionEntity
import app.isfa.kart.db.api.source.brand.KartBrandModel
import app.isfa.kart.db.api.source.subscription.CreateKartSubscriptionModel
import app.isfa.kart.db.api.source.subscription.KartSubscriptionDataSource
import app.isfa.kart.db.api.source.subscription.KartSubscriptionModel
import com.isfa.kart.str.StringProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class KartSubscriptionDataSourceImpl(
    private val dao: KartSubscriptionDao,
    private val stringProvider: StringProvider
) : KartSubscriptionDataSource {

    override fun subscriptions(): Flow<List<KartSubscriptionModel>> {
        return dao.all().map { list ->
            list.map { joined ->
                KartSubscriptionModel(
                    id = joined.subscription.id,
                    accountId = joined.subscription.accountId,
                    cardType = joined.subscription.cardType,
                    subscriptionType = joined.subscription.subscriptionType,
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

    override suspend fun insert(model: CreateKartSubscriptionModel): Result<Boolean> {
        if (subscriptions().first().any { it.accountId == model.accountId }) {
            return Result.failure(Throwable(stringProvider.duplicateAccountId()))
        }

        val insertion = dao.insert(
            KartSubscriptionEntity(
                accountId = model.accountId,
                cardType = model.cardType,
                subscriptionType = model.subscriptionType,
                brandSlug = model.brandSlug,
                expirationDate = model.expirationDate
            )
        )

        return if (insertion > 0) {
            Result.success(true)
        } else {
            Result.failure(Throwable(stringProvider.unexpectedError()))
        }
    }

    override suspend fun update(model: CreateKartSubscriptionModel) {
        dao.update(
            KartSubscriptionEntity(
                accountId = model.accountId,
                cardType = model.cardType,
                subscriptionType = model.subscriptionType,
                brandSlug = model.brandSlug,
                expirationDate = model.expirationDate
            )
        )
    }

    override suspend fun delete(subscriptionId: Int) {
        dao.delete(subscriptionId)
    }
}
