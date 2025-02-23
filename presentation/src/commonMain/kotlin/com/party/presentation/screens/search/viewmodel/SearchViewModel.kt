package com.party.presentation.screens.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.core.domain.onError
import com.party.core.domain.onSuccess
import com.party.domain.usecase.party.GetPartyListUseCase
import com.party.domain.usecase.party.GetRecruitmentListUseCase
import com.party.domain.usecase.search.GetSearchedDataUseCase
import com.party.domain.usecase.user.GetPositionsUseCase
import com.party.presentation.enum.OrderDescType
import com.party.presentation.enum.PartyType
import com.party.presentation.screens.search.SearchAction
import com.party.presentation.screens.search.SearchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSearchedDataUseCase: GetSearchedDataUseCase,
    private val getPartyListUseCase: GetPartyListUseCase,
    private val getRecruitmentListUseCase: GetRecruitmentListUseCase,
    private val getPositionsUseCase: GetPositionsUseCase,
): ViewModel(){

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    // 전체 영역 검색하기
    private fun allSearch(titleSearch: String, page: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchState.update { it.copy(isLoadingAllSearch = true) }

            getSearchedDataUseCase(
                titleSearch = titleSearch,
                page = page,
                limit = limit,
            ).onSuccess { result ->
                _searchState.update {
                    it.copy(
                        isLoadingAllSearch = false,
                        allSearchedList = result
                    )
                }
            }.onError {
                _searchState.update { it.copy(isLoadingAllSearch = false) }
            }
        }
    }

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
            _searchState.update { it.copy(isLoadingParty = true) }
            getPartyListUseCase(
                page = page,
                limit = limit,
                sort = sort,
                order = order,
                titleSearch = titleSearch,
                status = status,
                partyTypes = partyTypes,
            ).onSuccess { result ->
                _searchState.update {
                    it.copy(
                        partySearchedList = result,
                        isLoadingParty = false
                    )
                }
            }.onError {
                _searchState.update { it.copy(isLoadingParty = false) }
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
            _searchState.update { it.copy(isLoadingRecruitment = true) }
            getRecruitmentListUseCase(
                page = page,
                limit = limit,
                sort = sort,
                order = order,
                titleSearch = titleSearch,
                partyTypes = partyTypes,
                position = position
            ).onSuccess { result ->
                _searchState.update {
                    it.copy(
                        recruitmentSearchedList = result,
                        isLoadingRecruitment = false
                    )
                }
            }.onError {
                _searchState.update { it.copy(isLoadingRecruitment = false) }
            }
        }
    }

    // 서브 포지션 조회
    private fun getSubPositionList(
        main: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchState.update { it.copy(isLoadingSubPosition = true) }

            getPositionsUseCase(
                main = main
            ).onSuccess { result ->
                _searchState.update {
                    it.copy(
                        isLoadingSubPosition = false,
                        getSubPositionList = result
                    )
                }
            }.onError {
                _searchState.update {
                    it.copy(isLoadingSubPosition = false)
                }
            }
        }
    }

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnNavigationBack -> {}
            is SearchAction.OnInputKeywordChange -> _searchState.update { it.copy(inputKeyword = action.keyword) }
            is SearchAction.OnTabClick -> {
                _searchState.update { it.copy(selectedTabText = action.tabText) }

                when (_searchState.value.selectedTabText) {
                    "전체" -> allSearch(
                        titleSearch = _searchState.value.inputKeyword,
                        page = 1,
                        limit = 50
                    )

                    "파티" -> getPartyList(
                        titleSearch = _searchState.value.inputKeyword,
                        page = 1,
                        limit = 50,
                        sort = "createdAt",
                        order = OrderDescType.DESC.type,
                        status = _searchState.value.isActiveParty
                    )

                    "모집공고" -> getRecruitmentList(
                        titleSearch = _searchState.value.inputKeyword,
                        page = 1,
                        limit = 50,
                        sort = "createdAt",
                        order = OrderDescType.DESC.type,
                        partyTypes = emptyList(),
                        position = emptyList()
                    )
                }
            }
            is SearchAction.OnIsShowKeywordAreaChange -> _searchState.update { it.copy(isShowKeywordArea = action.isShowKeywordArea) }

            is SearchAction.OnSearch -> {
                _searchState.update { it.copy(isShowKeywordArea = false) } // 검색 버튼 클릭 시 키워드 영역을 숨긴다.
                //insertKeyword(_searchState.value.inputKeyword)

                when (_searchState.value.selectedTabText) {
                    "전체" -> allSearch(
                        titleSearch = _searchState.value.inputKeyword,
                        page = 1,
                        limit = 50
                    )

                    "파티" -> getPartyList(
                        titleSearch = _searchState.value.inputKeyword,
                        page = 1,
                        limit = 50,
                        sort = "createdAt",
                        order = OrderDescType.DESC.type,
                        status = _searchState.value.isActiveParty
                    )

                    /*"모집공고" -> getRecruitmentList(
                        _searchState.value.inputKeyword,
                        1,
                        size = 50,
                        sort = "createdAt",
                        order = OrderDescType.DESC.type,
                    )*/
                }
            }

            is SearchAction.OnDeleteKeyword -> {}
            is SearchAction.OnAllDeleteKeyword -> {}
            is SearchAction.OnChangeOrderByParty -> {
                _searchState.update { currentState ->
                    val sortedList = if (action.isDesc) {
                        currentState.partySearchedList.parties.sortedByDescending { it.createdAt }
                    } else {
                        currentState.partySearchedList.parties.sortedBy { it.createdAt }
                    }
                    currentState.copy(
                        isDescParty = action.isDesc,
                        partySearchedList = currentState.partySearchedList.copy(parties = sortedList)
                    )
                }
            }

            is SearchAction.OnChangeActive -> {
                _searchState.update { it.copy(isActiveParty = action.status) }
                getPartyList(
                    titleSearch = _searchState.value.inputKeyword,
                    page = 1,
                    limit = 50,
                    sort = "createdAt",
                    order = OrderDescType.DESC.type,
                    status = action.status
                )
            }

            is SearchAction.OnPartyTypeModelClose -> _searchState.update { it.copy(isPartyTypeSheetOpen = action.isVisibleModal)}

            // 클릭시 이미 저장되있으면 삭제하고 없으면 추가한다.
            is SearchAction.OnSelectedPartyType -> {
                _searchState.update { state ->
                    val updatedTypeList = state.selectedTypeListParty.toMutableList().apply {
                        if (action.partyType == "전체") {
                            clear() // "전체"가 들어오면 리스트를 전부 삭제
                            add("전체")
                        } else {
                            remove("전체") // "전체" 외의 값이 들어오면 "전체" 삭제
                            if (contains(action.partyType)) remove(action.partyType) else add(
                                action.partyType
                            )
                        }
                    }
                    state.copy(selectedTypeListParty = updatedTypeList)
                }
            }

            is SearchAction.OnPartyTypeReset -> {
                _searchState.update { it.copy(selectedTypeListParty = emptyList()) }
                _searchState.update { it.copy(selectedTypeListRecruitment = emptyList()) }
                _searchState.update { it.copy(
                    selectedMainPosition = "",
                    selectedSubPositionList = emptyList(),
                    selectedMainAndSubPosition = emptyList(),
                ) }
            }

            is SearchAction.OnPartyTypeApply -> {
                _searchState.update { it.copy(isPartyTypeSheetOpen = false) }

                val selectedTypeList: List<String> = _searchState.value.selectedTypeListParty
                getPartyList(
                    titleSearch = _searchState.value.inputKeyword,
                    page = 1,
                    limit = 50,
                    sort = "createdAt",
                    order = OrderDescType.DESC.type,
                    status = _searchState.value.isActiveParty,
                    partyTypes = selectedTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    }
                )
            }

            is SearchAction.OnChangeOrderByRecruitment -> {
                _searchState.update { currentState ->
                    val sortedList = if (action.isDesc) {
                        currentState.recruitmentSearchedList.partyRecruitments.sortedByDescending { it.createdAt }
                    } else {
                        currentState.recruitmentSearchedList.partyRecruitments.sortedBy { it.createdAt }
                    }
                    currentState.copy(
                        isDescRecruitment = action.isDesc,
                        recruitmentSearchedList = currentState.recruitmentSearchedList.copy(
                            partyRecruitments = sortedList
                        )
                    )
                }
            }

            is SearchAction.OnPartyTypeRecruitment -> _searchState.update { it.copy(isPartyTypeSheetOpenRecruitment = action.isVisibleModal) }

            is SearchAction.OnSelectedPartyTypeRecruitment -> {
                _searchState.update { state ->
                    val updatedTypeList = state.selectedTypeListRecruitment.toMutableList().apply {
                        if (action.partyType == "전체") {
                            clear() // "전체"가 들어오면 리스트를 전부 삭제
                            add("전체")
                        } else {
                            remove("전체") // "전체" 외의 값이 들어오면 "전체" 삭제
                            if (contains(action.partyType)) remove(action.partyType) else add(
                                action.partyType
                            )
                        }
                    }
                    state.copy(selectedTypeListRecruitment = updatedTypeList)
                }
            }

            is SearchAction.OnPartyTypeApply2 -> {
                _searchState.update {
                    it.copy(
                        isPartyTypeSheetOpenRecruitment = false,
                        selectedTypeListSize = _searchState.value.selectedTypeListRecruitment.size
                    )
                }

                val matchingIds = _searchState.value.selectedSubPositionList.filter { position ->
                    _searchState.value.selectedMainAndSubPosition.any { it.second == position.sub }
                }.map { it.id }

                val selectedTypeList: List<String> =
                    _searchState.value.selectedTypeListRecruitment
                getRecruitmentList(
                    titleSearch = _searchState.value.inputKeyword,
                    page = 1,
                    limit = 50,
                    sort = "createdAt",
                    order = OrderDescType.DESC.type,
                    partyTypes = selectedTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    position = matchingIds
                )
            }
            is SearchAction.OnPositionSheetClose -> _searchState.update { it.copy(isPositionSheetOpen = action.isVisible) }
            is SearchAction.OnPositionSheetOpenClick -> _searchState.update { it.copy(isPositionSheetOpen = true) }
            is SearchAction.OnMainPositionClick -> {
                _searchState.update { it.copy(selectedMainPosition = action.mainPosition) }
                getSubPositionList(action.mainPosition)
            }
            is SearchAction.OnSubPositionClick -> {
                _searchState.update { state ->
                    val updatedSubPositionList = state.selectedSubPositionList.toMutableList().apply {
                        if (any { it.sub == action.subPosition }) {
                            // 이미 선택된 서브 포지션을 제거
                            val newList = filterNot { it.sub == action.subPosition }
                            clear()
                            addAll(newList)
                        } else {
                            // 새로운 서브 포지션을 추가
                            state.getSubPositionList.find { it.sub == action.subPosition }?.let { add(it) }
                        }
                    }

                    // 기존 메인과 서브 포지션 조합 유지하며 업데이트
                    val updatedMainAndSubPosition = state.selectedMainAndSubPosition.toMutableList().apply {
                        // 서브 포지션 클릭으로 업데이트된 조합을 반영
                        val newList = filterNot { pair -> pair.first == state.selectedMainPosition && pair.second == action.subPosition }
                        clear()
                        addAll(newList)

                        updatedSubPositionList.find { it.sub == action.subPosition }?.let {
                            add(state.selectedMainPosition to it.sub) // KMP에서도 동작하는 Pair 사용
                        }
                    }

                    state.copy(
                        selectedSubPositionList = updatedSubPositionList,
                        selectedMainAndSubPosition = updatedMainAndSubPosition
                    )
                }
            }

            is SearchAction.OnDelete -> {

            }
            is SearchAction.OnPositionApply -> {
                _searchState.update { it.copy(isPositionSheetOpen = false) }

                val matchingIds = _searchState.value.selectedSubPositionList.filter { position ->
                    _searchState.value.selectedMainAndSubPosition.any { it.second == position.sub }
                }.map { it.id }

                // matchingIds 리스트 반환 또는 다음 동작 수행
                getRecruitmentList(
                    titleSearch = _searchState.value.inputKeyword,
                    page = 1,
                    limit = 50,
                    sort = "createdAt",
                    order = OrderDescType.DESC.type,
                    partyTypes = _searchState.value.selectedTypeListRecruitment.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    position = matchingIds
                )
            }
        }
    }
}
