package app.isfa.kart.repository.fake

import app.isfa.kart.db.api.source.subscription.CreateKartSubscriptionModel
import app.isfa.kart.db.api.source.subscription.KartSubscriptionDataSource
import app.isfa.kart.db.api.source.subscription.KartSubscriptionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSubscriptionDataSource(
    initialSubscriptions: List<KartSubscriptionModel> = emptyList(),
) : KartSubscriptionDataSource {

    private val flow = MutableStateFlow(initialSubscriptions)

    val insertedModels = mutableListOf<CreateKartSubscriptionModel>()
    val updatedModels = mutableListOf<CreateKartSubscriptionModel>()
    val deletedIds = mutableListOf<Int>()

    var insertResult: Result<Boolean> = Result.success(true)

    fun emit(subs: List<KartSubscriptionModel>) { flow.value = subs }

    override fun subscriptions(): Flow<List<KartSubscriptionModel>> = flow

    override suspend fun insert(model: CreateKartSubscriptionModel): Result<Boolean> {
        insertedModels += model
        return insertResult
    }

    override suspend fun update(model: CreateKartSubscriptionModel) { updatedModels += model }
    override suspend fun delete(subscriptionId: Int) { deletedIds += subscriptionId }
}