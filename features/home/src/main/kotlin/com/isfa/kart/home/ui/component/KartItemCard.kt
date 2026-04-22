package com.isfa.kart.home.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun KartItemCard(
    favIconUrl: String,
    brandName: String,
    accountId: String,
    colors: String, // "#FFF,#000"
    onCardClicked: (accountId: String) -> Unit,
) {
    val gradientColors = remember(colors) {
        colors.split(",").map { 
            val baseColor = it.toColor()
            // Darken the colors slightly to make them feel more "matte" and premium
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
            .clip(MaterialTheme.shapes.large)
            .clickable { onCardClicked(accountId) },
        shape = MaterialTheme.shapes.large,
        color = Color.Transparent,
        border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.15f)) // Elegant "glass" edge
    ) {
        Box(
            modifier = Modifier
                .background(brandBrush)
                // Add a "Satin" overlay for professional depth
                .drawWithCache {
                    val highlight = Brush.verticalGradient(
                        0.0f to Color.White.copy(alpha = 0.12f),
                        0.5f to Color.Transparent,
                        1.0f to Color.Black.copy(alpha = 0.12f)
                    )
                    onDrawWithContent {
                        drawContent()
                        // Removed BlendMode.Overlay to improve scroll performance
                        drawRect(highlight)
                    }
                }
                .padding(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (favIconUrl.isNotEmpty()) {
                        AsyncImage(
                            model = favIconUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.15f)),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.1f))
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Column {
                    Text(
                        text = brandName,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.5).sp
                        ),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = accountId.uppercase(),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            letterSpacing = 1.sp
                        ),
                        color = Color.White.copy(alpha = 0.7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

fun String.toColor(): Color {
    val hex = removePrefix("#")

    val formatted = when (hex.length) {
        3 -> "FF" + hex.map { "$it$it" }.joinToString("") // #RGB → #RRGGBB
        6 -> "FF$hex"                                     // #RRGGBB → add alpha
        8 -> hex                                          // #AARRGGBB
        else -> throw IllegalArgumentException("Invalid hex color: $this")
    }

    return Color(formatted.toLong(16))
}