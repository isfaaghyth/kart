package app.isfa.kart.db.api.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.MerchantCategory
import kotlinx.serialization.SerialName

@Entity(
    tableName = "kart_members",
    foreignKeys = [
        ForeignKey(
            entity = KartBrandInfoEntity::class,
            parentColumns = ["slug"],
            childColumns = ["brand_slug"],
            onDelete = ForeignKey.RESTRICT,
        )
    ],
    indices = [Index(value = ["brand_slug"])]
)
data class KartMembershipEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val accountId: String,
    val cardType: CardType,
    val category: MerchantCategory,
    val brandSlug: String,
)