package com.isfa.kart.home.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isfa.kart.design.KartCard
import com.isfa.kart.design.KartSpacing

@Composable
fun KartItemCard(
    brandName: String,
    accountId: String,
    onCardClicked: (accountId: String) -> Unit,
) {
    KartCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClicked(accountId) }
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(KartSpacing.paragraph)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                brandName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                accountId.uppercase(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}