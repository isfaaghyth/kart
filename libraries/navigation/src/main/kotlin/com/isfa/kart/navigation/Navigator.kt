package com.isfa.kart.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavKey

val LocalNavigator = compositionLocalOf<KartNavigator> { error("No Navigator provided") }

class KartNavigator(private val backStack: MutableList<NavKey>) {

    fun navigateTo(route: Navigation) = backStack.add(route)

    fun navigateAndClear(route: Navigation) {
        backStack.clear()
        backStack.add(route)
    }

    fun goBack() = backStack.removeLastOrNull()
}