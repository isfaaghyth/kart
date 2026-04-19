package app.isfa.kart.db.api.source.subscription

import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.SubscriptionType
import app.isfa.kart.db.api.source.brand.KartBrandModel

data class KartSubscriptionModel(
    val id: Int,
    val accountId: String,
    val cardType: CardType,
    val subscriptionType: SubscriptionType,
    val brand: KartBrandModel,
)

data class CreateKartSubscriptionModel(
    val brandSlug: String,
    val accountId: String,
    val cardType: CardType,
    val subscriptionType: SubscriptionType,
)
