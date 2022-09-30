package com.example.enforcmemntapp.data.source.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {

    val Context.dataStore: DataStore<Preferences>

    suspend fun insertUserId(id: Int)

    fun getUserId(): Flow<Int?>

    suspend fun setStreet(street:String)

    fun getStreet():Flow<String?>
}