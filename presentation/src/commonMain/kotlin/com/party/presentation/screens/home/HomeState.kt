package com.party.presentation.screens.home

import com.party.core.presentation.bottomsheet.positionList
import com.party.core.presentation.homeTopTabList
import com.party.domain.model.party.PartyList
import com.party.domain.model.party.RecruitmentList
import com.party.domain.model.user.detail.PositionList

data class HomeState(

    var isScrollParty: Boolean = false,
    var isScrollRecruitment: Boolean = false,
    val isExpandedFloating: Boolean = false,

    // 메인 / 파티 / 모집공고 탭
    val selectedTabText: String = homeTopTabList[0],

    // Main 모집공고
    val isLoadingRecruitmentList: Boolean = false,
    val recruitmentList: RecruitmentList = RecruitmentList(emptyList(), 0),

    // Main 파티
    val isLoadingPartyList: Boolean = false,
    val partyList: PartyList = PartyList(emptyList(), 0),

    // Party Area - 파티유형 bottom sheet
    val isPartyTypeSheetOpen: Boolean = false,
    val selectedPartyTypeListParty: List<String> = emptyList(),

    // Party Area - 진행중 토글
    val isActivePartyToggle: Boolean = true,

    // Party Area - 등록일 순 내림차순
    val isDescPartyArea: Boolean = true,

    // RecruitmentArea
    val isPositionSheetOpen: Boolean = false,
    val isPartyTypeSheetOpenRecruitment: Boolean = false,
    val isDescRecruitment: Boolean = true,

    val selectedMainPosition: String = positionList[0],
    val getSubPositionList: List<PositionList> = emptyList(),
    val selectedSubPositionList: List<PositionList> = emptyList(),
    val selectedMainAndSubPosition: List<Pair<String, String>> = emptyList(),

    val selectedPartyTypeListRecruitment: List<String> = emptyList(),
)