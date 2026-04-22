package com.isfa.kart.home

import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.repository.api.KartCardUiModel

data class HomeUiState(
    val cards: List<KartCardUiModel>,
    val categories: List<MerchantCategory>,
) {

    companion object {
        val Empty = HomeUiState(
            cards = emptyList(),
            categories = emptyList()
        )
    }
}