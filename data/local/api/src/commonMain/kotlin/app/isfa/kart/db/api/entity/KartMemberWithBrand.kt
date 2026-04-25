package app.isfa.kart.db.api.entity

import androidx.room.Embedded
import androidx.room.Relation
import app.isfa.kart.db.api.entity.KartBrandInfoEntity


data class KartMemberWithBrand(
    @Embedded
    val member: KartMembershipEntity,

    @Relation(
        parentColumn = "brand_slug",
        entityColumn = "slug",
    )
    val brand: KartBrandInfoEntity,
)
