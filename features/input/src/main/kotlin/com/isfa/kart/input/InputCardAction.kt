package com.isfa.kart.input

import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.SubscriptionType

sealed interface InputCardAction {
    data class AddMemberCard(
        val brandSlug: String,
        val accountId: String,
        val cardType: CardType
    ) : InputCardAction

    data class AddSubscriptionCard(
        val brandSlug: String,
        val accountId: String,
        val cardType: CardType,
        val subscriptionType: SubscriptionType,
        val expirationDate: Long
    ) : InputCardAction
}