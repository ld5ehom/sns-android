package com.ld5ehom.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Creates a DataStore instance with the name "user_datastore"
// (이름이 "user_datastore"인 DataStore 인스턴스 생성)
private val Context.dataStore by preferencesDataStore(name = "user_datastore")

// Injects the Context to access the DataStore (DataStore에 접근하기 위한 Context 주입)
class UserDataStore @Inject constructor(
    private val context: Context
) {
    // Defines a key for storing the token in the DataStore (DataStore에 토큰을 저장하기 위한 키 정의)
    companion object {
        private val KEY_TOKEN = stringPreferencesKey("token")
    }

    // Saves the token to the DataStore
    suspend fun setToken(token: String) {
        context.dataStore.edit { pref ->
            pref[KEY_TOKEN] = token  // Stores the token using the defined key
        }
    }

    // Retrieves the token from the DataStore (DataStore에서 토큰을 검색)
    suspend fun getToken(): String? {
        return context.dataStore.data.map {
            it[KEY_TOKEN]
        }.firstOrNull()  // Returns the token or null if not found
    }

    // Clears all stored preferences in the DataStore
    suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }
}
