package com.isfa.kart.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.foundation.clickable
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.db.api.SubscriptionType
import app.isfa.kart.db.api.source.brand.KartBrandModel
import com.isfa.kart.design.KartActionChip
import com.isfa.kart.design.KartButton
import com.isfa.kart.design.KartTextField
import com.isfa.kart.design.KartTheme
import com.isfa.kart.input.di.InputCardViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCardBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    viewModel: InputCardViewModel = viewModel(factory = InputCardViewModelFactory)
) {
    val brandList by viewModel.brandList.collectAsStateWithLifecycle()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .width(48.dp)
                    .height(6.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.extraLarge
                    )
            )
        }
    ) {
        InputCardScreen(
            onDismissRequest = onDismissRequest,
            brandList = brandList,
            onSaveClick = { viewModel.sendEvent(it) }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InputCardScreen(
    onDismissRequest: () -> Unit = {},
    brandList: List<KartBrandModel>,
    onSaveClick: (InputCardAction) -> Unit = {}
) {

    val scrollState = rememberScrollState()

    var merchantName by remember { mutableStateOf("") }
    var merchantSlug by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var cardType by remember { mutableStateOf("Member") } // Member or Subscription
    var expandedMerchantList by remember { mutableStateOf(false) }
    var expandedSubscription by remember { mutableStateOf(false) }
    var subscriptionType by remember { mutableStateOf("Monthly") }

    var showDatePicker by remember { mutableStateOf(false) }
    var expirationDate by remember { mutableStateOf<Long?>(null) }
    val datePickerState = rememberDatePickerState()

    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    val filteredBrands = remember(merchantName, brandList) {
        if (merchantName.isEmpty()) {
            brandList
        } else {
            brandList.filter { it.name.contains(merchantName, ignoreCase = true) }
        }
    }

    val subscriptionOptions = listOf("Daily", "Weekly", "Monthly", "Annual")

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    expirationDate = datePickerState.selectedDateMillis
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Add a new Kart",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Brand / Merchant Name
        ExposedDropdownMenuBox(
            expanded = expandedMerchantList,
            onExpandedChange = { expandedMerchantList = it }
        ) {
            KartTextField(
                value = merchantName,
                onValueChange = {
                    merchantName = it
                    merchantSlug = "" // Clear slug if user is typing manually
                    expandedMerchantList = it.isNotEmpty()
                },
                label = "Brand Name".uppercase(),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMerchantList) }
            )

            if (expandedMerchantList && filteredBrands.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = expandedMerchantList,
                    onDismissRequest = { expandedMerchantList = false }
                ) {
                    filteredBrands.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(text = option.name, style = MaterialTheme.typography.bodyLarge)
                                    Text(
                                        text = option.category.merchantName,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                }
                            },
                            onClick = {
                                merchantName = option.name
                                merchantSlug = option.slug
                                expandedMerchantList = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }
12
        Spacer(modifier = Modifier.height(16.dp))

        // Card Number / ID
        KartTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            label = "Card Number or ID".uppercase(),
            placeholder = "1234 5678 9012",
            isMono = true,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Include any spaces or dashes as they appear on the card.",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Card Type (Member vs Subscription)
        Text(
            text = "Card Type".uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = cardType == "Member",
                    onClick = { cardType = "Member" }
                )
                Text(
                    text = "Member",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = cardType == "Subscription",
                    onClick = { cardType = "Subscription" }
                )
                Text(
                    text = "Subscription",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        // Subscription Type Dropdown (conditionally shown)
        if (cardType == "Subscription") {
            Spacer(modifier = Modifier.height(16.dp))
            ExposedDropdownMenuBox(
                expanded = expandedSubscription,
                onExpandedChange = { expandedSubscription = it }
            ) {
                KartTextField(
                    value = subscriptionType,
                    onValueChange = {},
                    label = "Subscription Type",
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSubscription) }
                )
                ExposedDropdownMenu(
                    expanded = expandedSubscription,
                    onDismissRequest = { expandedSubscription = false }
                ) {
                    subscriptionOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                subscriptionType = option
                                expandedSubscription = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Expiration Date
            Box(modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true }) {
                KartTextField(
                    value = expirationDate?.let { dateFormatter.format(Date(it)) } ?: "",
                    onValueChange = {},
                    label = "Expiration Date".uppercase(),
                    placeholder = "Select Date",
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = null // Optional: could add a calendar icon
                )
                // Overlay to catch clicks since KartTextField might be focusing
                Box(modifier = Modifier.matchParentSize().background(Color.Transparent).clickable { showDatePicker = true })
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        KartButton(
            text = "Save $cardType",
            onClick = {
                val action = if (cardType == "Member") {
                    InputCardAction.AddMemberCard(
                        brandSlug = merchantSlug,
                        accountId = cardNumber,
                        cardType = CardType.Barcode // Defaulting to Barcode for now
                    )
                } else {
                    val subType = when (subscriptionType) {
                        "Daily" -> SubscriptionType.Daily
                        "Weekly" -> SubscriptionType.Weekly
                        "Monthly" -> SubscriptionType.Monthly
                        "Annual" -> SubscriptionType.Annual
                        else -> SubscriptionType.None
                    }
                    InputCardAction.AddSubscriptionCard(
                        brandSlug = merchantSlug,
                        accountId = cardNumber,
                        cardType = CardType.Numeric, // Defaulting to Numeric for subscription
                        subscriptionType = subType,
                        expirationDate = expirationDate ?: 0L
                    )
                }
                onSaveClick(action)
                onDismissRequest()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = merchantSlug.isNotEmpty() && cardNumber.isNotEmpty()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputCardScreenPreview() {
    KartTheme {
        InputCardScreen(
            onDismissRequest = {},
            brandList = emptyList()
        )
    }
}
