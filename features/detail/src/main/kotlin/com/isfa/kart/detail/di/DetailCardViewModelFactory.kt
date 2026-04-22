package com.isfa.kart.detail.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.isfa.kart.repository.api.RepositoryDependencies
import com.isfa.kart.detail.DetailCardViewModel

val DetailCardViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
    initializer {
        val kartFetchCardRepository =
            (this[APPLICATION_KEY] as RepositoryDependencies).kartFetchCardRepository()

        DetailCardViewModel(
            cardRepository = kartFetchCardRepository
        )
    }
}