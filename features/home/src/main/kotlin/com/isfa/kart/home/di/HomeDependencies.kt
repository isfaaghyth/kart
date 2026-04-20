package com.isfa.kart.home.di

import app.isfa.kart.repository.api.KartAddCardRepository
import app.isfa.kart.repository.api.KartBrandInfoRepository
import app.isfa.kart.repository.api.KartCardRepository
import app.isfa.kart.repository.api.KartSearchCardRepository

interface HomeDependencies {

    fun kartFetchCardRepository(): KartCardRepository
    fun kartSearchCardRepository(): KartSearchCardRepository
    fun kartAddCardRepository(): KartAddCardRepository
    fun kartBrandInfoRepository(): KartBrandInfoRepository
}