package com.isfa.kart.home

import androidx.lifecycle.ViewModel
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeViewModel(private val dataSource: KartMembershipDataSource) : ViewModel() {

    val state: Flow<HomeUiState> =
        dataSource.members()
            .map {
                HomeUiState()
            }
}