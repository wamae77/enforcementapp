package com.example.enforcmemntapp.data.source.remote.models

data class QueryByReceiptResponse(
    val message: String,
    val response_data: ResponseDataX,
    val status: Int
)