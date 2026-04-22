package com.isfa.kart.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.repository.api.KartBrandInfoRepository
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartSearchCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _effect = MutableSharedFlow<HomeEffect>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val effect = _effect.asSharedFlow()

    val state = combine(keyword, selectedCategory) { k, c -> k to c }
        .flatMapLatest { (k, c) ->
            if (c != null) {
                kartSearchCardRepository.filterByCategory(c)
            } else {
                kartSearchCardRepository.search(k)
            }
        }
        .combine(kartFetchCardRepository.allCards()) { filtered, all ->
            val activeCategories = all.map { it.brand.category }.distinct()

            HomeUiState(
                state = HomeUiState.UiState.Succeed,
                cards = filtered,
                categories = activeCategories,
                keywordSearch = keyword.value,
                selectedCategory = selectedCategory.value
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

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnKeywordChanged -> updateKeyword(event.keyword)
            is HomeEvent.OnCategorySelected -> updateCategory(event.category)
            is HomeEvent.OnCardClicked -> {
                _effect.tryEmit(HomeEffect.NavigateToCardDetail(event.accountId))
            }
        }
    }

    private fun updateKeyword(newKeyword: String) {
        keyword.value = newKeyword
        // Clear category when searching by keyword to avoid conflicts
        if (newKeyword.isNotEmpty()) {
            selectedCategory.value = null
        }
    }

    private fun updateCategory(category: MerchantCategory?) {
        selectedCategory.value = category
        // Clear keyword when filtering by category
        if (category != null) {
            keyword.value = ""
        }
    }
}