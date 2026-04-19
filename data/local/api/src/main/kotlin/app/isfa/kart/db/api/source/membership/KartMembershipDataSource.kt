package app.isfa.kart.db.api.source.membership

import kotlinx.coroutines.flow.Flow

interface KartMembershipDataSource {

    fun members(): Flow<List<KartMembershipModel>>

    suspend fun insert(model: CreateKartMembershipModel)

    suspend fun update(model: CreateKartMembershipModel)

    suspend fun delete(cardId: Int)
}