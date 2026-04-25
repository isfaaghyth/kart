package app.isfa.kart.db.api.serializer

import app.isfa.kart.db.api.MerchantCategory
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object MerchantCategorySerializer : KSerializer<MerchantCategory> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("MerchantCategory", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: MerchantCategory) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): MerchantCategory {
        val value = decoder.decodeString()
        return MerchantCategory.entries.firstOrNull { it.name == value }
            ?: throw SerializationException("Unknown MerchantCategory: $value")
    }
}