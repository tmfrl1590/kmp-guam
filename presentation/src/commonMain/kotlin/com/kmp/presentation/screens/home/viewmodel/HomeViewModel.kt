package com.kmp.presentation.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.core.domain.onError
import com.kmp.core.domain.onSuccess
import com.kmp.domain.usecase.party.GetPartyListUseCase
import com.kmp.domain.usecase.party.GetRecruitmentListUseCase
import com.kmp.presentation.screens.home.HomeAction
import com.kmp.presentation.screens.home.HomeState
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
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoadingPartyList = true) }
            getPartyListUseCase(
                page = page,
                limit = limit,
                sort = sort,
                order = order,
                titleSearch = titleSearch,
                status = status,
                partyTypes = partyTypes
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

        }
    }
}