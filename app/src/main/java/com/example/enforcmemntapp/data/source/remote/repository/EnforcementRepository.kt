package com.example.enforcmemntapp.data.source.remote.repository

import com.example.enforcmemntapp.data.source.remote.models.*
import com.example.enforcmemntapp.utils.Resource

interface EnforcementRepository {

    suspend fun login(usrName: String, password: String): Resource<LoginResponse>

    suspend fun queryByReceiptIdNumber(
        ReceiptNo: String,
        UserID: String,
        Latitude: String,
        Longitude: String
    ): Resource<QueryByReceiptResponse>

    suspend fun checkBusinessValidity(
        businessID: String,
        userID: String
    ): Resource<CheckbusinessResponse>

    suspend fun getByNumberPlate(
        PlateNumber: String,
        UserID: String,
        Latitude: String,
        Longitude: String
    ): Resource<EnforcebyplatenumberRespoonse>

    suspend fun parking(
        PlateNumber: String,
        UserID: String,
        Latitude: String,
        Longitude: String,
        TownId: String
    ):Resource<ParkingResponse>

    suspend fun clampingFee():Resource<GetClampingFeeResponse>

    suspend fun getTown():Resource<GetTownResponse>

    suspend fun clamping(
        plateNumber: String,
        TownId: String,
        UserID: String,
        Latitude: String,
        Longitude: String,
        FeeId: String,
        PadLockNumber: String,
        clampTownId: String
    ):Resource<ClampingResponse>

    suspend fun getHistory(
        UserID: String
    ):Resource<HistoryResponse>

    suspend fun getParkingStreet():Resource<ParkingStreetResponse>
}