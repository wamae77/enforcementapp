package com.example.enforcmemntapp.data.source.remote.models

data class LoginResponse(
    val message: String,
    val response_data: ResponseData,
    val status: Int
)