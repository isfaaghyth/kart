package com.isfa.kart.design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KartGallery() {
    var textFieldValue by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Coffee") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            KartTopAppBar(
                onMenuClick = {},
                onSearchClick = {}
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        // The order of verticalScroll and fillMaxSize/padding is critical.
        // Applying padding(paddingValues) FIRST ensures we don't draw under the topBar.
        // Applying verticalScroll BEFORE internal padding ensures the scrollbar is at the edge.
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(KartSpacing.small)
        ) {
            // Typography Section
            Section(title = "Typography") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Display Large", style = MaterialTheme.typography.displayLarge)
                    Text("Headline Large", style = MaterialTheme.typography.headlineLarge)
                    Text("Body Large (Inter)", style = MaterialTheme.typography.bodyLarge)
                    Text("Label Medium (Inter)", style = MaterialTheme.typography.labelMedium)
                    Text("Barcode Metadata 12345", style = MaterialTheme.typography.bodySmall)
                }
            }

            // Cards & Layout Section
            Section(title = "Cards & Layout (No-Line Rule)") {
                KartCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            "Editorial Layout Card",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "This card uses tonal shifts instead of shadows or borders to define its boundaries.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Demonstrating Vertical Gap Divider
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("List Item One", modifier = Modifier.padding(vertical = 12.dp))
                    KartVerticalGapDivider()
                    Text("List Item Two", modifier = Modifier.padding(vertical = 12.dp))
                    KartVerticalGapDivider()
                    Text("List Item Three", modifier = Modifier.padding(vertical = 12.dp))
                }
            }

            // Input Fields Section
            Section(title = "Input Fields (Ghost Border)") {
                KartTextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = "Brand / Merchant Name",
                    placeholder = "e.g. Starbucks",
                    leadingIcon = Icons.Default.ShoppingCart
                )

                KartTextField(
                    value = "1234 5678 9012",
                    onValueChange = { },
                    label = "Card Number or ID",
                    leadingIcon = Icons.Default.AccountCircle,
                    isMono = true
                )
            }

            // Chips Section
            Section(title = "Action Chips") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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

            // Buttons Section
            Section(title = "Buttons") {
                KartButton(
                    text = "Save Card",
                    onClick = {},
                    icon = Icons.Default.CheckCircle
                )
            }

            Spacer(modifier = Modifier.height(KartSpacing.large))
        }
    }
}

@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.1.sp
            ),
            color = MaterialTheme.colorScheme.outline
        )
        content()
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun GalleryPreviewLight() {
    KartTheme(darkTheme = false) {
        KartGallery()
    }
}

@Preview(showBackground = true, name = "Dark Mode", backgroundColor = 0xFF121212)
@Composable
fun GalleryPreviewDark() {
    KartTheme(darkTheme = true) {
        KartGallery()
    }
}
