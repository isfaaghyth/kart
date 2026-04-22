package com.isfa.kart.detail.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.isfa.kart.repository.api.KartCardUiModel
import coil.compose.AsyncImage

@Composable
fun PhysicalCard(card: KartCardUiModel) {
    val colors = card.brand.colors
    val gradientColors = remember(colors) {
        colors.split(",").map { 
            val baseColor = it.toColor()
            baseColor.copy(
                red = baseColor.red * 0.9f,
                green = baseColor.green * 0.9f,
                blue = baseColor.blue * 0.9f
            )
        }
    }

    val brandBrush = remember(gradientColors) {
        if (gradientColors.size >= 2) {
            Brush.linearGradient(
                colors = gradientColors,
                start = androidx.compose.ui.geometry.Offset(0f, 0f),
                end = androidx.compose.ui.geometry.Offset.Infinite
            )
        } else {
            val singleColor = gradientColors.first()
            Brush.linearGradient(listOf(singleColor, singleColor))
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(220.dp),
        shape = MaterialTheme.shapes.large,
        color = Color.Transparent,
        border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.15f)),
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .background(brandBrush)
                .drawWithCache {
                    val highlight = Brush.verticalGradient(
                        0.0f to Color.White.copy(alpha = 0.12f),
                        0.5f to Color.Transparent,
                        1.0f to Color.Black.copy(alpha = 0.12f)
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(highlight, blendMode = BlendMode.Overlay)
                    }
                }
                .padding(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (card.brand.faviconUrl.isNotEmpty()) {
                        AsyncImage(
                            model = card.brand.faviconUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.15f)),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.1f))
                        )
                    }

                    Text(
                        text = card.brand.category.merchantName.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White.copy(alpha = 0.6f),
                            letterSpacing = 1.2.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Column {
                    Text(
                        text = card.brand.name,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.5).sp
                        ),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = card.accountId.uppercase(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            letterSpacing = 2.sp
                        ),
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

private fun String.toColor(): Color {
    val hex = removePrefix("#")
    val formatted = when (hex.length) {
        3 -> "FF" + hex.map { "$it$it" }.joinToString("")
        6 -> "FF$hex"
        8 -> hex
        else -> "FF000000"
    }
    return Color(formatted.toLong(16))
}
