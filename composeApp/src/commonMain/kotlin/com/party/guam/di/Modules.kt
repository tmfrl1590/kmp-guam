package com.party.guam.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.party.core.data.HttpClientFactory
import com.party.data.datasource.local.datastore.createDataStore
import com.party.data.network.party.PartyRemoteSource
import com.party.data.network.party.PartyRemoteSourceImpl
import com.party.data.network.user.UserRemoteSourceImpl
import com.party.data.network.user.UserRemoteSource
import com.party.data.repository.PartyRepositoryImpl
import com.party.data.repository.UserRepositoryImpl
import com.party.domain.repository.DataStoreRepository
import com.party.data.repository.DataStoreRepositoryImpl
import com.party.domain.repository.PartyRepository
import com.party.domain.repository.UserRepository
import com.party.domain.usecase.datastore.GetAccessTokenUseCase
import com.party.domain.usecase.datastore.SaveAccessTokenUseCase
import com.party.domain.usecase.party.GetPartyListUseCase
import com.party.domain.usecase.party.GetRecruitmentListUseCase
import com.party.domain.usecase.user.GetPositionsUseCase
import com.party.domain.usecase.user.GoogleLoginUseCase
import com.party.presentation.screens.home.viewmodel.HomeViewModel
import com.party.presentation.screens.login.LoginViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::LoginViewModel)
}

val useCaseModule = module {
    factory { GetPartyListUseCase(get()) }
    factory { GetRecruitmentListUseCase(get()) }
    factory { GetPositionsUseCase(get()) }
    factory { GoogleLoginUseCase(get()) }

    // DataStore
    factory { SaveAccessTokenUseCase(get()) }
    factory { GetAccessTokenUseCase(get()) }
}

val repositoryModule = module {
    singleOf(::DataStoreRepositoryImpl).bind<DataStoreRepository>()
    singleOf(::PartyRepositoryImpl).bind<PartyRepository>()
    singleOf(::UserRepositoryImpl).bind<UserRepository>()
}

val remoteSourceModule = module {
    singleOf(::PartyRemoteSourceImpl).bind<PartyRemoteSource>()
    singleOf(::UserRemoteSourceImpl).bind<UserRemoteSource>()
}