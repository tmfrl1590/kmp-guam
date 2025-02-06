package com.kmp.presentation.screens.home

import com.kmp.domain.model.party.PartyList

data class HomeState(

    val partyList: PartyList = PartyList(emptyList(), 0),
)