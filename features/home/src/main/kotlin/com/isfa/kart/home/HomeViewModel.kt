package com.isfa.kart.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.db.api.source.brand.KartBrandModel
import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.membership.KartMembershipDataSource
import app.isfa.kart.db.api.source.subscription.CreateKartSubscriptionModel
import app.isfa.kart.repository.api.KartAddCardRepository
import app.isfa.kart.repository.api.KartBrandInfoRepository
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartSearchCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val kartFetchCardRepository: KartCardRepository,
    private val kartSearchCardRepository: KartSearchCardRepository,
    private val kartAddCardRepository: KartAddCardRepository,
    private val kartBrandInfoRepository: KartBrandInfoRepository
) : ViewModel() {

    val data = kartFetchCardRepository
        .allCards()
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            kartBrandInfoRepository.prefetch()
        }
    }

    fun addMemberCard(model: CreateKartMembershipModel) {
        viewModelScope.launch {
            kartAddCardRepository.addMember(model)
        }
    }

    fun addSubscriptionCard(model: CreateKartSubscriptionModel) {
        viewModelScope.launch {
            kartAddCardRepository.addSubscription(model)
        }
    }
}