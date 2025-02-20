package com.party.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.party.core.Constants.ACCESS_TOKEN_KEY
import com.party.data.datasource.local.datastore.createDataStore
import com.party.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(
    //private val dataStore: DataStore<Preferences>
): DataStoreRepository{
    private val dataStore = createDataStore()

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey(ACCESS_TOKEN_KEY)
    }

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = token
        }
    }

    override fun getAccessToken(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is Exception) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                val token = preferences[ACCESS_TOKEN] ?: ""
                token
            }
    }
}