package app.isfa.kart.repository

import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import app.isfa.kart.db.api.source.membership.KartMembershipModel
import app.isfa.kart.db.api.source.subscription.KartSubscriptionDataSource
import app.isfa.kart.db.api.source.subscription.KartSubscriptionModel
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartCardUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class KartCardRepositoryImpl(
    private val membershipDataSource: KartMembershipDataSource,
    private val subscriptionDataSource: KartSubscriptionDataSource,
) : KartCardRepository {

    override fun allCards(): Flow<List<KartCardUiModel>> = combine(
        membershipDataSource.members(),
        subscriptionDataSource.subscriptions(),
    ) { members, subs ->
        (members.map { it.toCard() } + subs.map { it.toCard() }).sortedByDescending { it.id }
    }

    override suspend fun cardDetail(accountId: String): KartCardUiModel? {
        return allCards().first().find { it.accountId == accountId }
    }

    override fun members(): Flow<List<KartCardUiModel.Member>> =
        membershipDataSource.members().map { list -> list.map { it.toCard() } }

    override fun subscriptions(): Flow<List<KartCardUiModel.Subscription>> =
        subscriptionDataSource.subscriptions().map { list -> list.map { it.toCard() } }

    private fun KartMembershipModel.toCard() = KartCardUiModel.Member(
        id = id,
        accountId = accountId,
        cardType = cardType,
        brand = brand,
    )

    private fun KartSubscriptionModel.toCard() = KartCardUiModel.Subscription(
        id = id,
        accountId = accountId,
        cardType = cardType,
        brand = brand,
        subscriptionType = subscriptionType,
    )
}
