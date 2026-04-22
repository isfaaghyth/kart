package com.isfa.kart.home.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.isfa.kart.repository.api.RepositoryDependencies
import com.isfa.kart.home.HomeViewModel

val HomeViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
    initializer {
        val kartFetchCardRepository =
            (this[APPLICATION_KEY] as RepositoryDependencies).kartFetchCardRepository()
        val kartSearchCardRepository =
            (this[APPLICATION_KEY] as RepositoryDependencies).kartSearchCardRepository()
        val kartBrandInfoRepository =
            (this[APPLICATION_KEY] as RepositoryDependencies).kartBrandInfoRepository()

        HomeViewModel(
            kartFetchCardRepository = kartFetchCardRepository,
            kartSearchCardRepository = kartSearchCardRepository,
            kartBrandInfoRepository = kartBrandInfoRepository
        )
    }
}