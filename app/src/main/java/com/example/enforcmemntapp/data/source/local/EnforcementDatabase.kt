package com.example.enforcmemntapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.enforcmemntapp.data.source.local.dao.UserdataDao
import com.example.enforcmemntapp.data.source.local.entities.UserdataEntities

@Database(
    entities = [UserdataEntities::class],
    exportSchema = true,
    version = 1
)
abstract class EnforcementDatabase : RoomDatabase() {

    abstract fun UserdataDao():UserdataDao
}