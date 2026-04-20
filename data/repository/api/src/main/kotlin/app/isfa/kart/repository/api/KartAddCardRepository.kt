package app.isfa.kart.repository.api

import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.subscription.CreateKartSubscriptionModel

interface KartAddCardRepository {

    /**
     * Adds a new membership card.
     * [CreateKartMembershipModel.brandSlug] must reference a known brand in [brand_infos].
     */
    suspend fun addMember(model: CreateKartMembershipModel)

    /**
     * Adds a new subscription card.
     * [CreateKartSubscriptionModel.brandSlug] must reference a known brand in [brand_infos].
     */
    suspend fun addSubscription(model: CreateKartSubscriptionModel)
}