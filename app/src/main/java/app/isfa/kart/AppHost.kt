package app.isfa.kart

import com.isfa.kart.home.HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.isfa.kart.detail.DetailCardScreen
import com.isfa.kart.navigation.KartNavigator
import com.isfa.kart.navigation.LocalNavigator
import com.isfa.kart.navigation.Navigation

@Composable
fun AppHost() {
    val backStack = remember { mutableStateListOf<NavKey>(Navigation.Home) }
    val navigator = remember(backStack) { KartNavigator(backStack) }

    CompositionLocalProvider(LocalNavigator provides navigator) {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<Navigation.Home> { HomeScreen() }
                entry<Navigation.Detail> { DetailCardScreen() }
            }
        )
    }
}