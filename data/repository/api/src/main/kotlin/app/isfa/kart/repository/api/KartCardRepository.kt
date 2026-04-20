package app.isfa.kart.repository.api

import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.subscription.CreateKartSubscriptionModel
import kotlinx.coroutines.flow.Flow

interface KartCardRepository {

    /**
     * Emits all membership + subscription cards combined, sorted newest-first by id.
     * Use this for the main home feed.
     */
    fun allCards(): Flow<List<KartCardUiModel>>

    /** Emits only [KartCardUiModel.Member] cards, sorted newest-first. */
    fun members(): Flow<List<KartCardUiModel.Member>>

    /** Emits only [KartCardUiModel.Subscription] cards, sorted newest-first. */
    fun subscriptions(): Flow<List<KartCardUiModel.Subscription>>
}
