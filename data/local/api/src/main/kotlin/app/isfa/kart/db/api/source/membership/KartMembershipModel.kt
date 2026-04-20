package app.isfa.kart.db.api.source.membership

import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.source.brand.KartBrandModel

data class KartMembershipModel(
    val id: Int,
    val accountId: String,
    val cardType: CardType,
    val brand: KartBrandModel,
)

data class CreateKartMembershipModel(
    val brandSlug: String,
    val accountId: String,
    val cardType: CardType,
)