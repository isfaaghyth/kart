package app.isfa.kart.db.api.source.brand

import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.MerchantCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KartBrandModel(

    @SerialName("slug")
    val slug: String,

    @SerialName("name")
    val name: String,

    @SerialName("type")
    val type: BrandTypeOf,

    @SerialName("category")
    val category: MerchantCategory,

    @SerialName("favicon")
    val faviconUrl: String,

    @SerialName("url")
    val url: String,

    @SerialName("colors")
    val colors: String,
)
