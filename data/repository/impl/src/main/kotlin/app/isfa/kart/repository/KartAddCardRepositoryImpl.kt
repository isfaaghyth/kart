package app.isfa.kart.repository

import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import app.isfa.kart.db.api.source.subscription.CreateKartSubscriptionModel
import app.isfa.kart.db.api.source.subscription.KartSubscriptionDataSource
import app.isfa.kart.repository.api.KartAddCardRepository

class KartAddCardRepositoryImpl(
    private val membershipDataSource: KartMembershipDataSource,
    private val subscriptionDataSource: KartSubscriptionDataSource,
) : KartAddCardRepository {

    override suspend fun addMember(model: CreateKartMembershipModel) =
        membershipDataSource.insert(model)

    override suspend fun addSubscription(model: CreateKartSubscriptionModel) =
        subscriptionDataSource.insert(model)
}