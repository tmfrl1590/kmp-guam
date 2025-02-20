package com.party.data.datasource.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.runBlocking

/*
fun createDataStore(context: Context): DataStore<Preferences> {
    return createDataStore {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}*/
actual fun createDataStore(): DataStore<Preferences> {
    return runBlocking {
        getDataStore(producePath = {
            ContextUtils.dataStoreApplicationContext!!.filesDir.resolve(
                dataStoreFileName
            ).absolutePath
        })
    }
}

object ContextUtils {

    var dataStoreApplicationContext: Context? = null

    fun setContext(context: Context) {
        dataStoreApplicationContext = context.applicationContext
    }
}