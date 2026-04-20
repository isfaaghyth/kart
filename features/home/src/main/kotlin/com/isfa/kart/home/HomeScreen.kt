package com.isfa.kart.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.SubscriptionType
import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.subscription.CreateKartSubscriptionModel
import com.isfa.kart.home.di.HomeViewModelFactory
import com.isfa.kart.navigation.LocalNavigator
import com.isfa.kart.navigation.Navigation

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory)) {
    val navigator = LocalNavigator.current
    val data by viewModel.data.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = data.toString(),
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    viewModel.addSubscriptionCard(
                        CreateKartSubscriptionModel(
                            brandSlug = "fore",
                            accountId = "8723467",
                            cardType = CardType.Barcode,
                            subscriptionType = SubscriptionType.Monthly
                        )
                    )
                    //navigator.navigateTo(Navigation.Detail)
                }
        )
    }
}

@Composable
fun HomeScreenPreview() {
    HomeScreen()
}