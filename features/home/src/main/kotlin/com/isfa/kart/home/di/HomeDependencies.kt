package com.isfa.kart.home.di

import app.isfa.kart.db.api.source.membership.KartMembershipDataSource

interface HomeDependencies {

    fun membershipDataSource(): KartMembershipDataSource
}