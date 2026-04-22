package com.isfa.kart.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = KartPrimary,
    onPrimary = Color.White,
    primaryContainer = KartPrimaryContainer,
    onPrimaryContainer = Color.White,
    secondary = KartSecondary,
    onSecondary = Color.White,
    secondaryContainer = KartSecondaryContainer,
    onSecondaryContainer = Color.Black,
    tertiary = KartTertiary,
    onTertiary = Color.Black,
    tertiaryContainer = KartTertiaryContainer,
    onTertiaryContainer = Color.White,
    error = KartError,
    onError = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color(0xFFEEEEEE),
    onSurfaceVariant = KartOnSurfaceVariant,
    surfaceContainerHighest = KartSurfaceContainerHighest,
    surfaceContainerHigh = KartSurfaceContainerHigh,
    surfaceContainer = KartSurfaceContainer,
    surfaceContainerLow = KartSurfaceContainerLow,
    surfaceContainerLowest = Color.Black, // Dark mode QR background should be black? Or maybe we stay white for scan reliability.
    outline = KartOutline,
    outlineVariant = KartOutlineVariant
)

private val LightColorScheme = lightColorScheme(
    primary = KartPrimary,
    onPrimary = Color.White,
    primaryContainer = KartPrimaryContainer,
    onPrimaryContainer = Color.White,
    secondary = KartSecondary,
    onSecondary = Color.White,
    secondaryContainer = KartSecondaryContainer,
    onSecondaryContainer = Color.Black,
    tertiary = KartTertiary,
    onTertiary = Color.Black,
    tertiaryContainer = KartTertiaryContainer,
    onTertiaryContainer = Color.White,
    error = KartError,
    onError = Color.White,
    surface = KartSurface,
    onSurface = KartOnSurface,
    onSurfaceVariant = KartOnSurfaceVariant,
    surfaceContainerHighest = KartSurfaceContainerHighest,
    surfaceContainerHigh = KartSurfaceContainerHigh,
    surfaceContainer = KartSurfaceContainer,
    surfaceContainerLow = KartSurfaceContainerLow,
    surfaceContainerLowest = KartSurfaceContainerLowest,
    outline = KartOutline,
    outlineVariant = KartOutlineVariant
)

@Composable
fun KartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
