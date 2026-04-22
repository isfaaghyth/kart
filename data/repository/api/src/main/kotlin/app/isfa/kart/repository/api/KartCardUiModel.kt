package app.isfa.kart.repository.api

import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.SubscriptionType
import app.isfa.kart.db.api.source.brand.KartBrandModel

sealed class KartCardUiModel {
    abstract val id: Int
    abstract val accountId: String
    abstract val cardType: CardType
    abstract val brand: KartBrandModel

    data class Member(
        override val id: Int,
        override val accountId: String,
        override val cardType: CardType,
        override val brand: KartBrandModel,
    ) : KartCardUiModel()

    data class Subscription(
        override val id: Int,
        override val accountId: String,
        override val cardType: CardType,
        override val brand: KartBrandModel,
        val subscriptionType: SubscriptionType,
    ) : KartCardUiModel()

    fun isSubscription() = this as? Subscription
}
