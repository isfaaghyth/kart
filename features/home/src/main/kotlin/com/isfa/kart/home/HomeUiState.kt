package com.isfa.kart.home

import app.isfa.kart.db.api.source.membership.KartMembershipModel

data class HomeUiState(
    val lastOpened: List<KartMembershipModel>,
    val members: List<KartMembershipModel>
) {

    companion object {
        val Empty = HomeUiState(
            lastOpened = emptyList(),
            members = emptyList()
        )
    }
}