package com.isfa.kart.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.isfa.kart.navigation.Navigation

@Composable
fun DetailCardScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Ini adalah detail page",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {

                }
        )
    }
}

@Composable
@Preview
fun DetailCardScreenPreview() {
    DetailCardScreen()
}