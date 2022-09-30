package com.example.enforcmemntapp.data.source.remote

import com.example.enforcmemntapp.data.source.remote.models.*
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface EnforcementApi {

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @FieldMap partmap: Map<String, String>,
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("activatepassword")
    suspend fun activatePassword(
        @FieldMap partmap: Map<String, String>
    ): Response<ActivatePasswordResponse>

    @FormUrlEncoded
    @POST("queryreceiptnumber")
    suspend fun queryByReceiptIdNumber(
        @FieldMap partmap: Map<String, String>
    ): Response<QueryByReceiptResponse>

    @FormUrlEncoded
    @POST("checkbusiness")
    suspend fun checkbusiness(
        @FieldMap partmap: Map<String, String>
    ): Response<CheckbusinessResponse>

    @FormUrlEncoded
    @POST("enforcebyplatenumber")
    suspend fun enforcebyplatenumber(
        @FieldMap partmap: Map<String, String>
    ): Response<EnforcebyplatenumberRespoonse>

    @FormUrlEncoded
    @POST("parking")
    suspend fun parking(
        @FieldMap partmap: Map<String, String>
    ): Response<ParkingResponse>

    @GET("clampingfee")
    suspend fun getClampingFee(): Response<GetClampingFeeResponse>

    @GET("town")
    suspend fun getTown(): Response<GetTownResponse>

    @FormUrlEncoded
    @POST("clamping")
    suspend fun clamping(
        @FieldMap partmap: Map<String, String>
    ): Response<ClampingResponse>

    @FormUrlEncoded
    @POST("history")
    suspend fun geHistory(
        @FieldMap partmap: Map<String, String>
    ): Response<HistoryResponse>

    @GET("parkingstreet")
    suspend fun getParkingStreet(): Response<ParkingStreetResponse>

    companion object {
        const val BASE_url = "http://142.93.140.202/county/api/enforcement/"
    }
}