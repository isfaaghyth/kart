package com.isfa.kart.detail

import androidx.lifecycle.ViewModel
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartCardUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailCardViewModel(private val cardRepository: KartCardRepository) : ViewModel() {

    private var _data = MutableStateFlow<KartCardUiModel?>(null)
    val data = _data.asStateFlow()

    suspend fun fetchCardDetail(accountId: String) {
        _data.emit(cardRepository.cardDetail(accountId))
    }
}