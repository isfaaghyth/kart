package com.isfa.kart.home

import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.repository.api.KartCardUiModel

data class HomeUiState(
    val state: UiState,
    val cards: List<KartCardUiModel>,
    val categories: List<MerchantCategory>,
    val keywordSearch: String = "",
    val selectedCategory: MerchantCategory? = null,
) {

    fun shouldShowFloating() =
        cards.isNotEmpty() || keywordSearch.isNotEmpty() || selectedCategory != null

    sealed interface UiState {
        data object Loading : UiState
        data object Succeed : UiState
        data object Failed : UiState
    }

    companion object {
        val Empty = HomeUiState(
            state = UiState.Loading,
            cards = emptyList(),
            categories = emptyList(),
            keywordSearch = "",
            selectedCategory = null
        )
    }
}