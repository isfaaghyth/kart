package app.isfa.kart.repository.api

import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.MerchantCategory
import kotlinx.coroutines.flow.Flow

interface KartSearchCardRepository {

    /**
     * Searches all cards by [keyword] matched case-insensitively against:
     * - brand name (e.g. "uniq" matches "UNIQLO")
     * - account ID (e.g. partial number)
     *
     * Optionally narrows results to a specific [BrandTypeOf]:
     * - [BrandTypeOf.Membership] → only membership cards
     * - [BrandTypeOf.Subscription] → only subscription cards
     * - `null` (default) → search across both types
     *
     * A blank [keyword] with no [type] filter behaves identically to [allCards].
     */
    fun search(keyword: String, type: BrandTypeOf? = null): Flow<List<KartCardUiModel>>

    /**
     * Emits only cards whose brand belongs to the given [MerchantCategory].
     *
     * Examples:
     * - [MerchantCategory.Retail] → UNIQLO, H&M cards
     * - [MerchantCategory.Entertainment] → Netflix cards
     * - [MerchantCategory.Cloud] → iCloud, Google One cards
     */
    fun filterByCategory(category: MerchantCategory): Flow<List<KartCardUiModel>>
}