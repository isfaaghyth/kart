package com.isfa.kart.home.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.isfa.kart.i18n.R
import com.isfa.kart.design.KartSpacing

@Composable
fun HomeGreeting(shouldHidden: Boolean, onAccountIdHidden: (Boolean) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(KartSpacing.micro)) {
        Text(
            text = "Beta Testing".uppercase(),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.1.sp
            ),
            color = MaterialTheme.colorScheme.outline
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                stringResource(R.string.home_greeting_wave),
                style = MaterialTheme.typography.headlineMedium
            )

            IconButton(
                onClick = { onAccountIdHidden(!shouldHidden) },
                modifier = Modifier
                    .padding(start = 6.dp)
                    .clip(CircleShape)
                    .size(28.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Icon(
                    imageVector = if (shouldHidden) {
                        Icons.Default.VisibilityOff
                    } else {
                        Icons.Default.Visibility
                    },
                    contentDescription = "Toggle Visibility",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Text(
            stringResource(R.string.home_greeting_caption),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}