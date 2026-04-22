package com.isfa.kart.home

import app.isfa.kart.repository.api.KartCardUiModel

data class HomeUiState(
    val cards: List<KartCardUiModel>
) {

    companion object {
        val Empty = HomeUiState(
            cards = emptyList(),
        )
    }
}