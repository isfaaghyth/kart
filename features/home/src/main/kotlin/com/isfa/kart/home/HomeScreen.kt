package com.isfa.kart.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isfa.kart.home.di.HomeViewModelFactory
import com.isfa.kart.home.ui.HomeScreenContent
import com.isfa.kart.navigation.LocalNavigator

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory)) {
    val navigator = LocalNavigator.current
    val data by viewModel.state.collectAsStateWithLifecycle()

    HomeScreenContent(
        state = data,
        onKeywordChange = viewModel::updateKeyword,
        onCategorySelect = viewModel::updateCategory
    )
}
