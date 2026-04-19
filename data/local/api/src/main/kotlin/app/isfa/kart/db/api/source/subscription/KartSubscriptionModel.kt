package app.isfa.kart.db.api.source.subscription

import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.SubscriptionType
import app.isfa.kart.db.api.source.brand.KartBrandModel

/**
 * Read model returned by [KartSubscriptionDataSource.subscriptions].
 * Contains the full joined brand information and the billing period.
 */
data class KartSubscriptionModel(
    val id: Int,
    val accountId: String,
    val cardType: CardType,
    /** Billing period for this subscription. */
    val subscriptionType: SubscriptionType,
    /** Resolved brand info from [brand_infos]. */
    val brand: KartBrandModel,
)

/**
 * Write model used for [KartSubscriptionDataSource.insert] and [KartSubscriptionDataSource.update].
 * Callers must supply a valid [brandSlug] that exists in [brand_infos].
 */
data class CreateKartSubscriptionModel(
    val brandSlug: String,
    val accountId: String,
    val cardType: CardType,
    val subscriptionType: SubscriptionType,
)
