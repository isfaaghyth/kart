package app.isfa.kart.db.api.converter

import androidx.room.TypeConverter
import app.isfa.kart.db.api.BrandTypeOf

object BrandTypeConverters {

    @TypeConverter
    fun fromType(type: BrandTypeOf) = type.toString()

    @TypeConverter
    fun fromString(str: String) = when (str) {
        "Membership" -> BrandTypeOf.Membership
        else -> BrandTypeOf.Subscription
    }
}