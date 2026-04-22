package app.isfa.kart.repository.api

import kotlinx.coroutines.flow.Flow

interface KartCardRepository {

    /**
     * Emits all membership + subscription cards combined, sorted newest-first by id.
     * Use this for the main home feed.
     */
    fun allCards(): Flow<List<KartCardUiModel>>

    /** Get card detail by card ID */
    suspend fun cardDetail(accountId: String): KartCardUiModel?

    /** Emits only [KartCardUiModel.Member] cards, sorted newest-first. */
    fun members(): Flow<List<KartCardUiModel.Member>>

    /** Emits only [KartCardUiModel.Subscription] cards, sorted newest-first. */
    fun subscriptions(): Flow<List<KartCardUiModel.Subscription>>
}
