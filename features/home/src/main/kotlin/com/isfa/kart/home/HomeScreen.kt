@file:OptIn(ExperimentalMaterial3Api::class)

package com.isfa.kart.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isfa.kart.home.di.HomeViewModelFactory
import com.isfa.kart.home.ui.HomeScreenContent
import com.isfa.kart.home.ui.HomeScreenEmptyContent
import com.isfa.kart.home.ui.HomeScreenLoadingContent
import com.isfa.kart.home.ui.HomeScreenScaffold
import com.isfa.kart.input.InputCardBottomSheet
import com.isfa.kart.navigation.LocalNavigator
import com.isfa.kart.navigation.Navigation
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory)) {
    val navigator = LocalNavigator.current
    val data by viewModel.state.collectAsStateWithLifecycle()

    var shouldShowInputCardBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest {
            when (it) {
                is HomeEffect.NavigateToCardDetail -> {
                    navigator.navigateTo(Navigation.Detail(it.accountId))
                }
            }
        }
    }

    HomeScreenScaffold(
        floatingActionButton = {
            if (data.shouldShowFloating()) {
                FloatingActionButton(
                    onClick = { shouldShowInputCardBottomSheet = true },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add New Card"
                    )
                }
            }
        },
        content = { paddingValues ->
            when (data.state) {
                is HomeUiState.UiState.Loading -> HomeScreenLoadingContent()
                is HomeUiState.UiState.Succeed -> {
                    if (data.shouldShowFloating()) {
                        HomeScreenContent(
                            state = data,
                            modifier = Modifier.padding(paddingValues),
                            onEvent = viewModel::onEvent
                        )
                    } else {
                        HomeScreenEmptyContent(modifier = Modifier.padding(paddingValues)) {
                            shouldShowInputCardBottomSheet = true
                        }
                    }
                }
                is HomeUiState.UiState.Failed -> {}
            }
        }
    )


    if (shouldShowInputCardBottomSheet) {
        InputCardBottomSheet(
            onDismissRequest = {
                shouldShowInputCardBottomSheet = !shouldShowInputCardBottomSheet
            }
        )
    }
}
