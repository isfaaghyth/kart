package app.isfa.kart.repository

import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartCardUiModel
import app.isfa.kart.repository.api.KartSearchCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KartSearchCardRepositoryImpl(private val repository: KartCardRepository) : KartSearchCardRepository {

    override fun search(keyword: String, type: BrandTypeOf?): Flow<List<KartCardUiModel>> =
        repository.allCards().map { cards ->
            cards.filter { card ->
                val matchesType = type == null || card.brand.type == type
                val matchesKeyword = keyword.isBlank()
                        || card.brand.name.contains(keyword, ignoreCase = true)
                        || card.accountId.contains(keyword, ignoreCase = true)
                matchesType && matchesKeyword
            }
        }

    override fun filterByCategory(category: MerchantCategory): Flow<List<KartCardUiModel>> =
        repository.allCards().map { cards ->
            cards.filter { card -> card.brand.category == category }
        }
}