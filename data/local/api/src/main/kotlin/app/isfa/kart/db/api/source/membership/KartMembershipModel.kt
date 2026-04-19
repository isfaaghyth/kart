package app.isfa.kart.db.api.source.membership

import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.MerchantCategory

data class KartMembershipModel(
    val id: Int,
    val accountId: String,
    val cardType: CardType,
    val merchantName: String,
    val merchantCategory: MerchantCategory,
)

data class CreateKartMembershipModel(
    val merchantName: String,
    val accountId: String,
    val cardType: CardType,
    val merchantCategory: MerchantCategory,
)