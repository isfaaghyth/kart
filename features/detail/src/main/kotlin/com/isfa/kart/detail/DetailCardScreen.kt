package com.isfa.kart.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isfa.kart.detail.di.DetailCardViewModelFactory
import com.isfa.kart.navigation.Navigation

@Composable
fun DetailCardScreen(
    accountId: String,
    viewModel: DetailCardViewModel = viewModel(factory = DetailCardViewModelFactory)
) {
    val data by viewModel.data.collectAsStateWithLifecycle()

    LaunchedEffect(accountId) {
        viewModel.fetchCardDetail(accountId)
    }

    data?.let { result ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = result.accountId,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {

                    }
            )
        }
    } ?: Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Uh no! It seems the card is invalid.",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview
fun DetailCardScreenPreview() {
    DetailCardScreen("")
}