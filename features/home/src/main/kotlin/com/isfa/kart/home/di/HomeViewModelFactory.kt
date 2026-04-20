package com.isfa.kart.home.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.isfa.kart.repository.api.KartAddCardRepository
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartSearchCardRepository
import com.isfa.kart.home.HomeViewModel

val HomeViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
    initializer {
        val kartFetchCardRepository =
            (this[APPLICATION_KEY] as HomeDependencies).kartFetchCardRepository()
        val kartSearchCardRepository =
            (this[APPLICATION_KEY] as HomeDependencies).kartSearchCardRepository()
        val kartAddCardRepository =
            (this[APPLICATION_KEY] as HomeDependencies).kartAddCardRepository()
        val kartBrandInfoRepository =
            (this[APPLICATION_KEY] as HomeDependencies).kartBrandInfoRepository()

        HomeViewModel(
            kartFetchCardRepository = kartFetchCardRepository,
            kartSearchCardRepository = kartSearchCardRepository,
            kartAddCardRepository = kartAddCardRepository,
            kartBrandInfoRepository = kartBrandInfoRepository
        )
    }
}