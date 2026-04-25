package app.isfa.kart.db.api.converter

import androidx.room.TypeConverter
import app.isfa.kart.db.api.SubscriptionType

object SubscriptionTypeConverters {

    private val subscriptionTypes = SubscriptionType.entries
        .associateBy(SubscriptionType::subType)

    @TypeConverter
    fun fromType(type: SubscriptionType) = type.subType

    @TypeConverter
    fun fromString(str: String) = subscriptionTypes[str] ?: SubscriptionType.None
}