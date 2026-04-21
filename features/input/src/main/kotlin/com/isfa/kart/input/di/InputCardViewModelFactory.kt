package com.isfa.kart.input.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.isfa.kart.repository.api.RepositoryDependencies
import com.isfa.kart.input.InputCardViewModel

val InputCardViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
    initializer {
        val kartAddCardRepository =
            (this[APPLICATION_KEY] as RepositoryDependencies).kartAddCardRepository()
        val kartBrandInfoRepository =
            (this[APPLICATION_KEY] as RepositoryDependencies).kartBrandInfoRepository()

        InputCardViewModel(
            kartAddCardRepository = kartAddCardRepository,
            kartBrandInfoRepository = kartBrandInfoRepository
        )
    }
}