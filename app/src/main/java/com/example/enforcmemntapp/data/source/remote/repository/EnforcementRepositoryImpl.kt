package com.example.enforcmemntapp.data.source.remote.repository

import com.example.enforcmemntapp.R
import com.example.enforcmemntapp.data.source.remote.EnforcementApi
import com.example.enforcmemntapp.data.source.remote.models.*
import com.example.enforcmemntapp.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EnforcementRepositoryImpl @Inject constructor(
    private val enforcementApi: EnforcementApi
) : EnforcementRepository {
    override suspend fun login(usrName: String, Password: String): Resource<LoginResponse> {
        return try {
            val map = mutableMapOf<String, String>()
            map["email"] = usrName
            map["password"] = Password
            val response = enforcementApi.login(map)
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }

    }

    override suspend fun queryByReceiptIdNumber(
        ReceiptNo: String,
        UserID: String,
        Latitude: String,
        Longitude: String
    ): Resource<QueryByReceiptResponse> {
        return try {
            val map = mutableMapOf<String, String>()
            map["ReceiptNo"] = ReceiptNo
            map["UserID"] = UserID
            map["Latitude"] = Latitude
            map["Longitude"] = Longitude
            val response = enforcementApi.queryByReceiptIdNumber(map)
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }
    }

    override suspend fun checkBusinessValidity(
        businessID: String,
        userID: String
    ): Resource<CheckbusinessResponse> {
        return try {
            val map = mutableMapOf<String, String>()
            map["BusinessID"] = businessID
            map["UserID"] = userID
            val response = enforcementApi.checkbusiness(map)
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }
    }

    override suspend fun getByNumberPlate(
        PlateNumber: String,
        UserID: String,
        Latitude: String,
        Longitude: String
    ): Resource<EnforcebyplatenumberRespoonse> {
        return try {
            val map = mutableMapOf<String, String>()
            map["PlateNumber"] = PlateNumber
            map["UserID"] = UserID
            map["Latitude"] = Latitude
            map["Longitude"] = Longitude
            val response = enforcementApi.enforcebyplatenumber(map)
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }
    }

    override suspend fun parking(
        PlateNumber: String,
        UserID: String,
        Latitude: String,
        Longitude: String,
        TownId: String
    ): Resource<ParkingResponse> {
        return try {
            val map = mutableMapOf<String, String>()
            map["PlateNumber"] = PlateNumber
            map["UserID"] = UserID
            map["Latitude"] = Latitude
            map["Longitude"] = Longitude
            map["TownId"] = TownId
            val response = enforcementApi.parking(map)
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }
    }

    override suspend fun clampingFee(): Resource<GetClampingFeeResponse> {
        return try {
            val response = enforcementApi.getClampingFee()
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }
    }

    override suspend fun getTown(): Resource<GetTownResponse> {
        return try {
            val response = enforcementApi.getTown()
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }
    }

    override suspend fun clamping(
        plateNumber: String,
        TownId: String,
        UserID: String,
        Latitude: String,
        Longitude: String,
        FeeId: String,
        PadLockNumber: String,
        clampTownId: String
    ): Resource<ClampingResponse> {
        return try {
            val map = mutableMapOf<String, String>()
            map["PlateNumber"] = plateNumber
            map["UserID"] = UserID
            map["Latitude"] = Latitude
            map["Longitude"] = Longitude
            map["TownId"] = TownId
            map["FeeId"] = FeeId
            map["PadLockNumber"] = PadLockNumber
            map["clampTownId"] = clampTownId
            val response = enforcementApi.clamping(map)
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }
    }

    override suspend fun getHistory(UserID: String): Resource<HistoryResponse> {
        return try {
            val map = mutableMapOf<String, String>()
            map["UserID"] = UserID
            val response = enforcementApi.geHistory(map)
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }
    }

    override suspend fun getParkingStreet(): Resource<ParkingStreetResponse> {
        return try {
            val response = enforcementApi.getParkingStreet()
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error(R.string.something_went_wrong)
            }
        } catch (e: Exception) {
            Resource.Error(R.string.something_went_wrong)
        }
    }
}