package com.isfa.kart.design

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(6.dp), // 0.375rem = 6dp
    large = RoundedCornerShape(12.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

val ChipShape = RoundedCornerShape(9999.dp)
val InputFieldShape = RoundedCornerShape(6.dp)
