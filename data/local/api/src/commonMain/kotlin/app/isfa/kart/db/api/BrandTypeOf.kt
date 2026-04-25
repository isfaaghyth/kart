package app.isfa.kart.db.api

import app.isfa.kart.db.api.serializer.BrandTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = BrandTypeSerializer::class)
sealed interface BrandTypeOf {
    @Serializable
    data object Membership : BrandTypeOf

    @Serializable
    data object Subscription : BrandTypeOf
}