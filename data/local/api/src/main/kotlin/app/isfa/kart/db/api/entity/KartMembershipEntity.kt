package app.isfa.kart.db.api.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import app.isfa.kart.db.api.CardType

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

    @ColumnInfo(name = "brand_slug")
    val brandSlug: String,
)