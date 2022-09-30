package com.example.enforcmemntapp.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class UserdataEntities(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val Email: String,
    val FirstName: String,
    val IDNo: String,
    val LastName: String,
    val OtherName: String,
    val UserID: String,
    val password_set: String,
    val phone: String
)
