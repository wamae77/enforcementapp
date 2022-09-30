package com.example.enforcmemntapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.enforcmemntapp.data.source.local.entities.UserdataEntities

@Dao
interface UserdataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: UserdataEntities): Long

    @Query("SELECT * FROM user_data WHERE id =:id")
    suspend fun getUserById(id: Int): UserdataEntities?
}