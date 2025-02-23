package com.party.presentation.screens.search

sealed interface SearchAction{
    data object OnNavigationBack: SearchAction
    data class OnInputKeywordChange(val keyword: String): SearchAction
    data class OnTabClick(val tabText: String): SearchAction
    data class OnIsShowKeywordAreaChange(val isShowKeywordArea: Boolean): SearchAction
    data object OnSearch: SearchAction
    data class OnDeleteKeyword(val keyword: String): SearchAction
    data object OnAllDeleteKeyword: SearchAction

    data class OnChangeOrderByParty(val isDesc: Boolean): SearchAction
    data class OnChangeActive(val status: String): SearchAction
    data class OnPartyTypeModelClose(val isVisibleModal: Boolean): SearchAction
    data class OnSelectedPartyType(val partyType: String): SearchAction
    data object OnPartyTypeReset: SearchAction
    data object OnPartyTypeApply: SearchAction

    data class OnPartyTypeRecruitment(val isVisibleModal: Boolean): SearchAction
    data class OnChangeOrderByRecruitment(val isDesc: Boolean): SearchAction
    data class OnSelectedPartyTypeRecruitment(val partyType: String): SearchAction
    data object OnPartyTypeApply2: SearchAction

    data class OnPositionSheetClose(val isVisible: Boolean): SearchAction
    data object OnPositionSheetOpenClick: SearchAction
    data class OnMainPositionClick(val mainPosition: String): SearchAction
    data class OnSubPositionClick(val subPosition: String): SearchAction
    data class OnDelete(val position: Pair<String, String>): SearchAction
    data object OnPositionApply: SearchAction
}