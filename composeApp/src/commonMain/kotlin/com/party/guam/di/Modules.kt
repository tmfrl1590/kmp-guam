package com.party.guam.di

import com.party.core.data.HttpClientFactory
import com.party.data.network.PartyRemoteSource
import com.party.data.network.PartyRemoteSourceImpl
import com.party.data.repository.PartyRepositoryImpl
import com.party.domain.repository.PartyRepository
import com.party.domain.usecase.party.GetPartyListUseCase
import com.party.domain.usecase.party.GetRecruitmentListUseCase
import com.party.presentation.screens.home.viewmodel.HomeViewModel
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