package com.isfa.kart.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.repository.api.KartBrandInfoRepository
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartSearchCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val kartFetchCardRepository: KartCardRepository,
    private val kartSearchCardRepository: KartSearchCardRepository,
    private val kartBrandInfoRepository: KartBrandInfoRepository
) : ViewModel() {

    private val keyword = MutableStateFlow("")
    private val selectedCategory = MutableStateFlow<MerchantCategory?>(null)

    val state = combine(keyword, selectedCategory) { k, c -> k to c }
        .flatMapLatest { (k, c) ->
            if (c != null) {
                kartSearchCardRepository.filterByCategory(c)
            } else {
                kartSearchCardRepository.search(k)
            }
        }
        .combine(kartFetchCardRepository.allCards()) { filtered, all ->
            // Extract unique categories from all cards for the filter chips
            val activeCategories = all.map { it.brand.category }.distinct()
            HomeUiState(
                cards = filtered,
                categories = activeCategories
            )
        }
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Empty
        )

    init {
        viewModelScope.launch {
            kartBrandInfoRepository.prefetch()
        }
    }

    fun updateKeyword(newKeyword: String) {
        keyword.value = newKeyword
        // Clear category when searching by keyword to avoid conflicts
        if (newKeyword.isNotEmpty()) {
            selectedCategory.value = null
        }
    }

    fun updateCategory(category: MerchantCategory?) {
        selectedCategory.value = category
        // Clear keyword when filtering by category
        if (category != null) {
            keyword.value = ""
        }
    }
}