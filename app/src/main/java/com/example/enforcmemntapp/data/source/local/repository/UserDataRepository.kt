package com.example.enforcmemntapp.data.source.local.repository

import com.example.enforcmemntapp.data.source.local.entities.UserdataEntities
import com.example.enforcmemntapp.data.source.remote.models.Userdata

interface UserDataRepository {

    suspend fun insertUserData(userdata: Userdata):Long

    suspend fun getUserData(id:Int):UserdataEntities?
}