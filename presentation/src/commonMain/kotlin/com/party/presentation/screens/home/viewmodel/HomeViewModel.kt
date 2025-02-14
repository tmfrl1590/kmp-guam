package com.party.presentation.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.core.domain.onError
import com.party.core.domain.onSuccess
import com.party.domain.model.user.detail.PositionList
import com.party.domain.usecase.party.GetPartyListUseCase
import com.party.domain.usecase.party.GetRecruitmentListUseCase
import com.party.domain.usecase.user.GetPositionsUseCase
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
    private val getPositionsUseCase: GetPositionsUseCase,
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
        partyTypes: List<Int>,
        position: List<Int>,
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

    private fun getSubPositionList(main: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPositionsUseCase(main = main)
                .onSuccess { result ->
                    _state.update { state -> state.copy(selectedMainPosition = main, getSubPositionList = result )}
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
            is HomeAction.OnDelete -> {
                _state.update { currentState ->
                    val updatedSubPositionList = currentState.selectedSubPositionList
                        .filter { it.sub != action.position.second } // removeIf 대체

                    val updatedMainAndSubPosition = currentState.selectedMainAndSubPosition
                        .filter { pair -> pair.first != action.position.first || pair.second != action.position.second } // removeIf 대체

                    currentState.copy(
                        selectedSubPositionList = updatedSubPositionList,
                        selectedMainAndSubPosition = updatedMainAndSubPosition
                    )
                }
            }

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
            is HomeAction.OnDescRecruitment -> {
                _state.update { currentState ->
                    val sortedList = if(action.isDesc){
                        currentState.recruitmentList.partyRecruitments.sortedByDescending { it.createdAt }
                    } else {
                        currentState.recruitmentList.partyRecruitments.sortedBy { it.createdAt }
                    }
                    currentState.copy(
                        isDescRecruitment = action.isDesc,
                        recruitmentList = currentState.recruitmentList.copy(partyRecruitments = sortedList)
                    )
                }
            }
            is HomeAction.OnExpandedFloating -> TODO()
            is HomeAction.OnMainPositionClick -> {
                _state.update { it.copy(selectedMainPosition = action.mainPosition) }
                getSubPositionList(action.mainPosition)
            }
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
            is HomeAction.OnPartyTypeApplyRecruitment -> {
                _state.update { it.copy(isPartyTypeSheetOpenRecruitment = false) }

                val selectedPartyTypeList = _state.value.selectedPartyTypeListRecruitment

                getRecruitmentList(
                    page = 1,
                    limit = 50,
                    sort = "createdAt",
                    order = OrderDescType.DESC.type,
                    titleSearch = null,
                    partyTypes = selectedPartyTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    position = emptyList()
                )
            }
            is HomeAction.OnPartyTypeSheetOpenRecruitment -> _state.update { it.copy(isPartyTypeSheetOpenRecruitment = action.isVisibleModal) }
            HomeAction.OnPersonalRecruitmentReload -> TODO()
            is HomeAction.OnPositionApply -> {
                _state.update { it.copy(isPositionSheetOpen = false) }
                val matchingIds = _state.value.selectedSubPositionList.filter { position ->
                    _state.value.selectedMainAndSubPosition.any { it.second == position.sub }
                }.map { it.id }

                getRecruitmentList(
                    page = 1,
                    limit = 50,
                    sort = "createdAt",
                    order = OrderDescType.DESC.type,
                    titleSearch = null,
                    partyTypes = _state.value.selectedPartyTypeListParty.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    position = matchingIds
                )
            }
            is HomeAction.OnPositionSheetOpen -> _state.update { it.copy(isPositionSheetOpen = action.isVisibleModal) }

            is HomeAction.OnPositionSheetReset -> {
                _state.update {
                    it.copy(
                        selectedMainPosition = "전체",
                        selectedSubPositionList = emptyList(),
                        selectedMainAndSubPosition = emptyList()
                    )
                }
            }

            is HomeAction.OnSelectedPartyTypeRecruitment -> {
                _state.update { state ->
                    val updatedList = state.selectedPartyTypeListRecruitment.toMutableList().apply {
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
                    state.copy(selectedPartyTypeListRecruitment = updatedList)
                }
            }

            is HomeAction.OnSelectedPartyTypeResetRecruitmentReset -> _state.update { it.copy(selectedPartyTypeListRecruitment = emptyList())}
            is HomeAction.OnSubPositionClick -> {
                _state.update { currentState ->
                    val updatedSubPositionList = if (currentState.selectedSubPositionList.any { it.sub == action.subPosition }) {
                        // 이미 선택된 서브 포지션을 제거
                        currentState.selectedSubPositionList.filter { it.sub != action.subPosition }
                    } else {
                        // 새로운 서브 포지션을 추가
                        currentState.selectedSubPositionList + currentState.getSubPositionList.find { it.sub == action.subPosition }
                    }.filterNotNull() // null 값 방지

                    val updatedMainAndSubPosition = currentState.selectedMainAndSubPosition
                        .filter { it.first != currentState.selectedMainPosition || it.second != action.subPosition } // 기존 removeIf 대체
                        .toMutableList()

                    // 서브 포지션이 추가된 경우 main과 sub의 조합을 추가
                    updatedSubPositionList.find { it.sub == action.subPosition }?.let {
                        updatedMainAndSubPosition.add(Pair(currentState.selectedMainPosition, it.sub))
                    }

                    currentState.copy(
                        selectedSubPositionList = updatedSubPositionList,
                        selectedMainAndSubPosition = updatedMainAndSubPosition
                    )
                }
            }

        }
    }
}