package com.example.enforcmemntapp.data.source.remote.models

data class Validparking(
    val CurrentState: String,
    val DateCreated: String,
    val Latitude: String,
    val Longitude: String,
    val NumberQueried: String,
    val queryfor: String,
    val whichitem: String
)