package app.isfa.kart.db.api.converter

import androidx.room.TypeConverter
import app.isfa.kart.db.api.CardType

object CardTypeConverters {

    private val types = CardType.entries.associateBy(CardType::type)

    @TypeConverter
    fun fromType(cardType: CardType) = cardType.type

    @TypeConverter
    fun fromString(str: String) = types[str] ?: CardType.Numeric
}