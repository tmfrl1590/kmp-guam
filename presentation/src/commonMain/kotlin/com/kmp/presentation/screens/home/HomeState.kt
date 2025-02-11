package com.kmp.presentation.screens.home

import com.kmp.core.presentation.homeTopTabList
import com.kmp.domain.model.party.PartyList
import com.kmp.domain.model.party.RecruitmentList

data class HomeState(

    // 메인 / 파티 / 모집공고 탭
    val selectedTabText: String = homeTopTabList[0],

    // Main 모집공고
    val isLoadingRecruitmentList: Boolean = false,
    val recruitmentList: RecruitmentList = RecruitmentList(emptyList(), 0),

    // Main 파티
    val isLoadingPartyList: Boolean = false,
    val partyList: PartyList = PartyList(emptyList(), 0),
)