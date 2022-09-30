package com.example.enforcmemntapp.data.source.local.repository

import com.example.enforcmemntapp.data.source.local.dao.UserdataDao
import com.example.enforcmemntapp.data.source.local.entities.UserdataEntities
import com.example.enforcmemntapp.data.source.remote.models.Userdata
import com.example.enforcmemntapp.data.source.toUserDataEntities
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepositoryImpl @Inject constructor(
    private val userdataDao: UserdataDao
) : UserDataRepository {
    override suspend fun insertUserData(userdata: Userdata): Long {
        return userdataDao.insert(userdata.toUserDataEntities())
    }

    override suspend fun getUserData(id: Int): UserdataEntities? {
        return userdataDao.getUserById(id)
    }
}