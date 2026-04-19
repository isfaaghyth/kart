package com.isfa.kart.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isfa.kart.home.di.HomeViewModelFactory
import com.isfa.kart.navigation.LocalNavigator
import com.isfa.kart.navigation.Navigation

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory)) {
    val navigator = LocalNavigator.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Hello World!",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    navigator.navigateTo(Navigation.Detail)
                }
        )
    }
}

@Composable
fun HomeScreenPreview() {
    HomeScreen()
}