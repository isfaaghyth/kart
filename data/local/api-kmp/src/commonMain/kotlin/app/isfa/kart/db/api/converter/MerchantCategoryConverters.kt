package app.isfa.kart.db.api.converter

import androidx.room.TypeConverter
import app.isfa.kart.db.api.MerchantCategory

object MerchantCategoryConverters {

    private val categories = MerchantCategory.entries
        .associateBy(MerchantCategory::merchantName)

    @TypeConverter
    fun fromCategory(category: MerchantCategory) = category.merchantName

    @TypeConverter
    fun fromString(str: String) = categories[str] ?: MerchantCategory.Other
}