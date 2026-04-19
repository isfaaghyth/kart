package com.isfa.kart.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Navigation : NavKey {

    @Serializable
    data object Home : Navigation

    @Serializable
    data object Detail : Navigation
}