package com.isfa.kart.detail

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.isfa.kart.db.api.CardType
import com.isfa.kart.detail.di.DetailCardViewModelFactory
import com.isfa.kart.detail.ui.DetailTopAppBar
import com.isfa.kart.detail.ui.PhysicalCard
import com.isfa.kart.detail.ui.ScannableCard
import com.isfa.kart.navigation.LocalNavigator

@Composable
fun DetailCardScreen(
    accountId: String,
    viewModel: DetailCardViewModel = viewModel(factory = DetailCardViewModelFactory)
) {
    val navigator = LocalNavigator.current
    val data by viewModel.data.collectAsStateWithLifecycle()

    LaunchedEffect(accountId) {
        viewModel.fetchCardDetail(accountId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                IconButton(
                    onClick = { navigator.goBack() },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .systemBarsPadding()
                ) {
                    Text(
                        text = "Kart",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            data?.let { card ->
                Spacer(modifier = Modifier.weight(1f))

                when (card.cardType) {
                    CardType.Card -> PhysicalCard(card = card)
                    CardType.Code -> ScannableCard(card = card)
                    CardType.Digital -> Text("ini digital")
                }

                Spacer(modifier = Modifier.weight(1f))

                // Action Area
                TextButton(
                    onClick = { /* Help */ },
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    Text(
                        text = "Having trouble scanning?",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
            } ?: run {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Uh no! It seems the card is invalid.",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
