package com.example.enforcmemntapp.data.source.models

import android.os.Parcel
import android.os.Parcelable

data class ParkingDataModel(
    val AmountPaid: Int?,
    val BillAmount: Int?,
    val BillDate: String?,
    val Category: String?,
    val ClampedAmount: Int?,
    val ClampedStatus: Int?,
    val CurrentState: String?,
    val Description: String?,
    val Duration: String?,
    val EndDate: String?,
    val PaidDate: String?,
    val ParkingFee: Int?,
    val SaccoName: String?,
    val StartDate: String?,
    val VehicleRegistration: String?,
    val VehicleType: String?,
    val zone: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(AmountPaid)
        parcel.writeValue(BillAmount)
        parcel.writeString(BillDate)
        parcel.writeString(Category)
        parcel.writeValue(ClampedAmount)
        parcel.writeValue(ClampedStatus)
        parcel.writeString(CurrentState)
        parcel.writeString(Description)
        parcel.writeString(Duration)
        parcel.writeString(EndDate)
        parcel.writeString(PaidDate)
        parcel.writeValue(ParkingFee)
        parcel.writeString(SaccoName)
        parcel.writeString(StartDate)
        parcel.writeString(VehicleRegistration)
        parcel.writeString(VehicleType)
        parcel.writeString(zone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParkingDataModel> {
        override fun createFromParcel(parcel: Parcel): ParkingDataModel {
            return ParkingDataModel(parcel)
        }

        override fun newArray(size: Int): Array<ParkingDataModel?> {
            return arrayOfNulls(size)
        }
    }


}
