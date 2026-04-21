package com.isfa.kart.home

import app.isfa.kart.db.api.source.membership.KartMembershipModel

data class HomeUiState(
    val cards: List<KartMembershipModel>
) {

    companion object {
        val Empty = HomeUiState(
            cards = emptyList(),
        )
    }
}