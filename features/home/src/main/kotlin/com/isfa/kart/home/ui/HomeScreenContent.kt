@file:OptIn(ExperimentalMaterial3Api::class)

package com.isfa.kart.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isfa.kart.design.KartActionChip
import com.isfa.kart.design.KartSpacing
import com.isfa.kart.design.KartTextField
import com.isfa.kart.design.KartTheme
import com.isfa.kart.home.HomeUiState
import com.isfa.kart.home.ui.component.HomeGreeting
import com.isfa.kart.home.ui.component.KartItemCard
import com.isfa.kart.input.InputCardBottomSheet

@Composable
fun HomeScreenContent(state: HomeUiState) {
    val scrollState = rememberLazyListState()

    var keywordSearch by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Coffee") }

    var shouldShowInputCardBottomSheet by remember { mutableStateOf(false) }

    // Sticky chip filter
    val showStickyChips by remember {
        derivedStateOf {
            val layoutInfo = scrollState.layoutInfo
            val chipsItem = layoutInfo.visibleItemsInfo.find { it.key == "filter_chips" }

            if (chipsItem != null) {
                chipsItem.offset < 0
            } else {
                scrollState.firstVisibleItemIndex > 4
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { shouldShowInputCardBottomSheet = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Card"
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp),
            state = scrollState,
            contentPadding = PaddingValues(top = 32.dp),
            verticalArrangement = Arrangement.spacedBy(KartSpacing.micro)
        ) {
            // Greeting
            item { HomeGreeting() }

            // Search Box
            item {
                KartTextField(
                    value = keywordSearch,
                    onValueChange = { keywordSearch = it },
                    placeholder = "e.g. Fore",
                    leadingIcon = Icons.Default.Search
                )
            }

            // Filter Chip
            stickyHeader(key = "filter_chips") {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (showStickyChips) {
                                MaterialTheme.colorScheme.surface
                            } else {
                                Color.Transparent
                            }
                        )
                ) {
                    val categories = listOf("Coffee", "Groceries", "Retail")
                    categories.forEach { category ->
                        KartActionChip(
                            label = category,
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                    KartActionChip(
                        label = "More",
                        onClick = {},
                        leadingIcon = Icons.Default.Add
                    )
                }
            }

            // Card List
            items(
                items = state.cards.chunked(2),
                key = { it.firstOrNull()?.accountId ?: 0 }
            ) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(KartSpacing.micro)
                ) {
                    rowItems.forEach { card ->
                        Box(modifier = Modifier.weight(1f)) {
                            KartItemCard(
                                favIconUrl = card.brand.faviconUrl,
                                brandName = card.brand.name,
                                accountId = card.accountId,
                                colors = card.brand.colors,
                                onCardClicked = {}
                            )
                        }
                    }
                }
            }
        }
    }

    if (shouldShowInputCardBottomSheet) {
        InputCardBottomSheet(
            onDismissRequest = { shouldShowInputCardBottomSheet = false }
        )
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    KartTheme {
        HomeScreenContent(HomeUiState.Empty)
    }
}
