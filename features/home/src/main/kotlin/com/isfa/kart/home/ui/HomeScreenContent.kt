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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.isfa.kart.db.api.MerchantCategory
import com.isfa.kart.design.KartActionChip
import com.isfa.kart.design.KartSpacing
import com.isfa.kart.design.KartTextField
import com.isfa.kart.design.KartTheme
import com.isfa.kart.home.HomeEvent
import com.isfa.kart.home.HomeUiState
import com.isfa.kart.home.ui.component.HomeGreeting
import com.isfa.kart.home.ui.component.KartItemCard

@Composable
fun HomeScreenContent(
    state: HomeUiState,
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit = {}
) {
    val scrollState = rememberLazyListState()

    var shouldHideAccountId by remember { mutableStateOf(false) }
    var searchKeyword by remember { mutableStateOf("") }

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

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        state = scrollState,
        contentPadding = PaddingValues(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(KartSpacing.micro)
    ) {
        // Greeting
        item {
            HomeGreeting(
                shouldHidden = shouldHideAccountId,
                onAccountIdHidden = { shouldHideAccountId = !shouldHideAccountId }
            )
        }

        // Search Box
        item {
            KartTextField(
                value = searchKeyword,
                onValueChange = {
                    searchKeyword = it
                    onEvent(HomeEvent.OnKeywordChanged(it))
                },
                placeholder = "e.g. Fore",
                leadingIcon = Icons.Default.Search,
                shouldAsSearchBox = true
            )
        }

        // Filter Chip
        stickyHeader(key = "filter_chips") {
            LazyRow(
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
                item {
                    KartActionChip(
                        label = "All",
                        selected = state.selectedCategory == null,
                        onClick = { onEvent(HomeEvent.OnCategorySelected(null)) }
                    )
                }

                items(state.categories) { category ->
                    KartActionChip(
                        label = category.merchantName,
                        selected = state.selectedCategory == category,
                        onClick = { onEvent(HomeEvent.OnCategorySelected(category)) }
                    )
                }
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
                for (i in 0 until 2) {
                    if (i < rowItems.size) {
                        val card = rowItems[i]
                        Box(modifier = Modifier.weight(1f)) {
                            KartItemCard(
                                favIconUrl = card.brand.faviconUrl,
                                brandName = card.brand.name,
                                accountId = if (shouldHideAccountId) {
                                    "***"
                                } else {
                                    card.accountId
                                },
                                colors = card.brand.colors,
                                onCardClicked = {
                                    onEvent(HomeEvent.OnCardClicked(card.accountId))
                                }
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    KartTheme {
        HomeScreenContent(
            state = HomeUiState.Empty,
            onEvent = {}
        )
    }
}
