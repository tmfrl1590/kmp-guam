package com.party.presentation.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.core.domain.onError
import com.party.core.domain.onSuccess
import com.party.domain.usecase.party.GetPartyListUseCase
import com.party.domain.usecase.party.GetRecruitmentListUseCase
import com.party.presentation.enum.OrderDescType
import com.party.presentation.enum.PartyType
import com.party.presentation.screens.home.HomeAction
import com.party.presentation.screens.home.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPartyListUseCase: GetPartyListUseCase,
    private val getRecruitmentListUseCase: GetRecruitmentListUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int> = emptyList(),
        titleSearch: String?,
        status: String?
    ){
        println("partyTypes $partyTypes")
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoadingPartyList = true) }
            getPartyListUseCase(
                page = page,
                limit = limit,
                sort = sort,
                order = order,
                titleSearch = titleSearch,
                status = status,
                partyTypes = partyTypes,
            ).onSuccess { result ->
                _state.update {
                    it.copy(
                        partyList = result,
                        isLoadingPartyList = false
                    )
                }
            }.onError {

            }
        }
    }

    fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>?,
        position: List<Int>?,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoadingRecruitmentList = true) }
            getRecruitmentListUseCase(
                page = page,
                limit = limit,
                sort = sort,
                order = order,
                titleSearch = titleSearch,
                partyTypes = partyTypes,
                position = position
            ).onSuccess { result ->
                _state.update {
                    it.copy(
                        recruitmentList = result,
                        isLoadingRecruitmentList = false
                    )
                }
            }.onError {

            }
        }
    }

    fun onAction(action: HomeAction) {
        when(action){
            is HomeAction.OnTabClick -> _state.update { it.copy(selectedTabText = action.tabText) }
            is HomeAction.OnPartyTypeSheetOpen -> _state.update { it.copy(isPartyTypeSheetOpen = action.isVisibleModal) }

            // 클릭시 이미 저장되있으면 삭제하고 없으면 추가한다.
            is HomeAction.OnSelectedPartyType -> {
                _state.update { state ->
                    val updatedList = state.selectedPartyTypeListParty.toMutableList().apply {
                        if(action.partyType == "전체"){
                            clear()
                            add("전체")
                        }else {
                            remove("전체")
                            if (contains(action.partyType)) remove(action.partyType) else add(
                                action.partyType
                            )
                        }
                    }
                    state.copy(selectedPartyTypeListParty = updatedList)
                }
            }
            is HomeAction.OnSelectedPartyTypeReset -> _state.update { it.copy(selectedPartyTypeListParty = emptyList()) }

            is HomeAction.OnActivePartyToggle -> {
                _state.update { it.copy(isActivePartyToggle = action.isActive) }
                getPartyList(
                    page = 1,
                    limit = 50,
                    sort = "createdAt",
                    order = OrderDescType.DESC.type,
                    partyTypes = _state.value.selectedPartyTypeListParty.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    titleSearch = null,
                    status = if(_state.value.isActivePartyToggle) "active" else "archived"
                )
            }
            is HomeAction.OnDelete -> TODO()
            is HomeAction.OnDescPartyArea -> {
                _state.update { currentState ->
                    val sortedList = if(action.isDesc){
                        currentState.partyList.parties.sortedByDescending { it.createdAt }
                    } else {
                        currentState.partyList.parties.sortedBy { it.createdAt }
                    }
                    currentState.copy(
                        isDescPartyArea = action.isDesc,
                        partyList = currentState.partyList.copy(parties = sortedList)
                    )
                }
            }
            is HomeAction.OnDescRecruitment -> TODO()
            is HomeAction.OnExpandedFloating -> TODO()
            is HomeAction.OnMainPositionClick -> TODO()
            is HomeAction.OnPartyTypeApply -> {
                _state.update { it.copy(isPartyTypeSheetOpen = false) }

                val selectedPartyTypeList = _state.value.selectedPartyTypeListParty
                getPartyList(
                    page = 1,
                    limit = 50,
                    sort = "createdAt",
                    order = OrderDescType.DESC.type,
                    partyTypes = selectedPartyTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    titleSearch = null,
                    status = null
                )
            }
            HomeAction.OnPartyTypeApplyRecruitment -> TODO()
            is HomeAction.OnPartyTypeSheetOpenRecruitment -> TODO()
            HomeAction.OnPersonalRecruitmentReload -> TODO()
            HomeAction.OnPositionApply -> TODO()
            is HomeAction.OnPositionSheetOpen -> TODO()
            HomeAction.OnPositionSheetReset -> TODO()

            is HomeAction.OnSelectedPartyTypeRecruitment -> TODO()

            HomeAction.OnSelectedPartyTypeResetRecruitmentReset -> TODO()
            is HomeAction.OnSubPositionClick -> TODO()
        }
    }
}