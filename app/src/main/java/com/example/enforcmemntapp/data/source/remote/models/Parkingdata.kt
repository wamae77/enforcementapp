package com.example.enforcmemntapp.data.source.remote.models

data class Parkingdata(
    val AmountPaid: Int,
    val BillAmount: Int,
    val BillDate: String,
    val Category: String,
    val ClampedAmount: Int,
    val ClampedStatus: Int,
    val CurrentState: String,
    val Description: String,
    val Duration: String,
    val EndDate: String,
    val PaidDate: String,
    val ParkingFee: Int,
    val SaccoName: String,
    val StartDate: String,
    val VehicleRegistration: String,
    val VehicleType: String,
    val zone: String
)