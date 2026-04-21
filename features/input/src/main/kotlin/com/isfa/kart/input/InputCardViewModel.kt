package com.isfa.kart.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.subscription.CreateKartSubscriptionModel
import app.isfa.kart.repository.api.KartAddCardRepository
import app.isfa.kart.repository.api.KartBrandInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InputCardViewModel(
    kartBrandInfoRepository: KartBrandInfoRepository,
    private val kartAddCardRepository: KartAddCardRepository,
) : ViewModel() {

    val brandList = kartBrandInfoRepository
        .brands()
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _action = MutableSharedFlow<InputCardAction>(replay = 1)

    init {
        viewModelScope.launch {
            _action
                .distinctUntilChanged()
                .collect(::onHandleEvent)
        }
    }

    fun sendEvent(action: InputCardAction) {
        _action.tryEmit(action)
    }

    private fun onHandleEvent(action: InputCardAction) {
        viewModelScope.launch {
            when (action) {
                is InputCardAction.AddMemberCard -> {
                    kartAddCardRepository.addMember(
                        CreateKartMembershipModel(
                            brandSlug = action.brandSlug,
                            accountId = action.accountId,
                            cardType = action.cardType
                        )
                    )
                }

                is InputCardAction.AddSubscriptionCard -> {
                    kartAddCardRepository.addSubscription(
                        CreateKartSubscriptionModel(
                            brandSlug = action.brandSlug,
                            accountId = action.accountId,
                            cardType = action.cardType,
                            subscriptionType = action.subscriptionType,
                            expirationDate = action.expirationDate
                        )
                    )
                }
            }
        }
    }
}