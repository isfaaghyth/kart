package app.isfa.kart.db.api.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "last_opened")
data class KartLastOpenedEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int = 0,

    @SerialName("member_id")
    val memberId: Int = 0,

    @SerialName("subscription_id")
    val subscriptionId: Int = 0,

    @SerialName("updated_at")
    val updatedAt: Long = 0,
)