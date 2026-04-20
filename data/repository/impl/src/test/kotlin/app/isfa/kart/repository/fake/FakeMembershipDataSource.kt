package app.isfa.kart.repository.fake

import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import app.isfa.kart.db.api.source.membership.KartMembershipModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMembershipDataSource(
    initialMembers: List<KartMembershipModel> = emptyList(),
) : KartMembershipDataSource {

    private val flow = MutableStateFlow(initialMembers)

    val insertedModels = mutableListOf<CreateKartMembershipModel>()
    val updatedModels = mutableListOf<CreateKartMembershipModel>()
    val deletedIds = mutableListOf<Int>()

    fun emit(members: List<KartMembershipModel>) { flow.value = members }

    override fun members(): Flow<List<KartMembershipModel>> = flow

    override suspend fun insert(model: CreateKartMembershipModel): Result<Boolean> {
        insertedModels += model
        return Result.success(true)
    }

    override suspend fun update(model: CreateKartMembershipModel) { updatedModels += model }
    override suspend fun delete(cardId: Int) { deletedIds += cardId }
}