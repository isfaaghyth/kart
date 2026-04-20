package app.isfa.kart.db.api.source.brand

import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.MerchantCategory

data class KartBrandModel(
    val slug: String,
    val name: String,
    val type: BrandTypeOf,
    val category: MerchantCategory,
    val faviconUrl: String,
    val url: String,
    val colors: String,
)
