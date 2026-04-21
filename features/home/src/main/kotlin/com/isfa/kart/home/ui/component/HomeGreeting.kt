package com.isfa.kart.home.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.isfa.kart.i18n.R
import com.isfa.kart.design.KartSpacing

@Composable
fun HomeGreeting() {
    Column(verticalArrangement = Arrangement.spacedBy(KartSpacing.micro)) {
        Text(
            text = "Tue 21 Apr".uppercase(),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.1.sp
            ),
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            stringResource(R.string.home_greeting_wave),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            stringResource(R.string.home_greeting_caption),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}