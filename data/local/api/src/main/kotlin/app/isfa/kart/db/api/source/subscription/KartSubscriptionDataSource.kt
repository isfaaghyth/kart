package app.isfa.kart.db.api.source.subscription

import kotlinx.coroutines.flow.Flow

interface KartSubscriptionDataSource {

    fun subscriptions(): Flow<List<KartSubscriptionModel>>

    suspend fun insert(model: CreateKartSubscriptionModel)

    suspend fun update(model: CreateKartSubscriptionModel)

    suspend fun delete(subscriptionId: Int)
}
