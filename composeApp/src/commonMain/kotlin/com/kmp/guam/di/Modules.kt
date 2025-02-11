package com.kmp.guam.di

import com.kmp.core.data.HttpClientFactory
import com.kmp.data.network.PartyRemoteSource
import com.kmp.data.network.PartyRemoteSourceImpl
import com.kmp.data.repository.PartyRepositoryImpl
import com.kmp.domain.repository.PartyRepository
import com.kmp.domain.usecase.party.GetPartyListUseCase
import com.kmp.domain.usecase.party.GetRecruitmentListUseCase
import com.kmp.presentation.screens.home.viewmodel.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}

val useCaseModule = module {
    factory { GetPartyListUseCase(get()) }
    factory { GetRecruitmentListUseCase(get()) }
}

val repositoryModule = module {
    singleOf(::PartyRepositoryImpl).bind<PartyRepository>()
}

val remoteSourceModule = module {
    singleOf(::PartyRemoteSourceImpl).bind<PartyRemoteSource>()
}