package app.isfa.kart.db.api.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.isfa.kart.db.api.BrandTypeOf
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "brand_infos")
data class KartBrandInfoEntity(
    @PrimaryKey
    @SerialName("slug")
    val slug: String,

    @SerialName("name")
    val name: String,

    @SerialName("type")
    val type: BrandTypeOf,

    @SerialName("favicon")
    val faviconUrl: String,

    @SerialName("url")
    val url: String,

    @SerialName("card_colors")
    val colors: String,
)