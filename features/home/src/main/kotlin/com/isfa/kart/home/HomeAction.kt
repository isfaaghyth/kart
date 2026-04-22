package com.isfa.kart.home

import app.isfa.kart.db.api.MerchantCategory

sealed interface HomeEvent {
    data class OnKeywordChanged(val keyword: String) : HomeEvent
    data class OnCategorySelected(val category: MerchantCategory?) : HomeEvent
    data class OnCardClicked(val accountId: String) : HomeEvent
}

sealed interface HomeEffect {
    class NavigateToCardDetail(val accountId: String) : HomeEffect
}