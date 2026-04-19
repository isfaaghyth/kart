package app.isfa.kart.db.api.entity

import androidx.room.Embedded
import androidx.room.Relation


data class KartMemberWithBrand(
    @Embedded
    val member: KartMembershipEntity,

    @Relation(
        parentColumn = "brand_slug",
        entityColumn = "slug",
    )
    val brand: KartBrandInfoEntity,
)
