package app.isfa.kart.db.api.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.MerchantCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "kart_members")
data class KartMembershipEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int = 0,

    @SerialName("account_id")
    val accountId: String,

    @SerialName("card_type")
    val cardType: CardType,

    @SerialName("merchant_name")
    val merchantName: String,

    @SerialName("merchant_category")
    val merchantCategory: MerchantCategory,
)