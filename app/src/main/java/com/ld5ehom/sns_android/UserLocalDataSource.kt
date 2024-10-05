package com.ld5ehom.sns_android

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val USER_PREFERENCES_NAME = "user_preferences"

// Data Store
private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)
class UserLocalDataSource constructor(
    private val context: Context
) {

    companion object{
        private val KEY_TOKEN = stringPreferencesKey("token")
    }

    suspend fun getToken(): String? {
        return context.dataStore.data.map { it[KEY_TOKEN] }.first()
    }

    suspend fun setToken(token:String){
        context.dataStore.edit { it[KEY_TOKEN] = token }
    }

    // Clears the Data Store upon logout 로그아웃 클리어함
    suspend fun clear(){
        context.dataStore.edit { it.clear() }
    }

}