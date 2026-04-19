package com.isfa.kart.home

import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel

sealed interface HomeAction {

    data class AddMemberCard(val model: CreateKartMembershipModel) : HomeAction
    data class RemoveMemberCard(val id: Int) : HomeAction
}