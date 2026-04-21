package app.isfa.kart.db.api.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.SubscriptionType

@Entity(
    tableName = "kart_subscriptions",
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
data class KartSubscriptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val accountId: String,

    val cardType: CardType,

    val subscriptionType: SubscriptionType = SubscriptionType.None,

    @ColumnInfo(name = "brand_slug")
    val brandSlug: String,

    val expirationDate: Long
)