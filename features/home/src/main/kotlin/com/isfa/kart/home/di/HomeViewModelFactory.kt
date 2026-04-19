package com.isfa.kart.home.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.isfa.kart.home.HomeViewModel

val HomeViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
    initializer {
        val membershipDataSource = (this[APPLICATION_KEY] as HomeDependencies).membershipDataSource()

        HomeViewModel(
            dataSource = membershipDataSource
        )
    }
}