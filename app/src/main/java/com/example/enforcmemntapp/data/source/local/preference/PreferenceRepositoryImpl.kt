package com.example.enforcmemntapp.data.source.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context
) : PreferenceRepository {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE)

    override suspend fun insertUserId(id: Int) {
        context.dataStore.edit {
            it[UserId] = id
        }
    }

    override fun getUserId(): Flow<Int?> {
        return context.dataStore.data.map {
            it[UserId]
        }

    }

    override suspend fun setStreet(street: String) {
        context.dataStore.edit {
            it[Street] = street
        }
    }

    override fun getStreet(): Flow<String?> {
        return context.dataStore.data.map {
            it[Street]
        }
    }

    companion object {
        const val PREFERENCE = "preference"
        val UserId = intPreferencesKey("preference")
        val Street = stringPreferencesKey("street")
    }
}