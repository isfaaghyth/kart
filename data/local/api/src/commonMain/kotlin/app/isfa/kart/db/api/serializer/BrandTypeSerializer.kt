package app.isfa.kart.db.api.serializer

import app.isfa.kart.db.api.BrandTypeOf
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object BrandTypeSerializer : KSerializer<BrandTypeOf> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("BrandTypeOf", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: BrandTypeOf) {
        encoder.encodeString(
            when (value) {
                is BrandTypeOf.Membership -> "Membership"
                is BrandTypeOf.Subscription -> "Subscription"
            }
        )
    }

    override fun deserialize(decoder: Decoder): BrandTypeOf {
        return when (decoder.decodeString()) {
            "Membership" -> BrandTypeOf.Membership
            "Subscription" -> BrandTypeOf.Subscription
            else -> throw SerializationException("Unknown BrandTypeOf")
        }
    }
}