package com.example.enforcmemntapp.data.source.models

import android.os.Parcel
import android.os.Parcelable

data class BusinessesDataModel(
    val ActivityCode: String?,
    val ActivityDescription: String?,
    val AmountBilled: String?,
    val AmountPaid: String?,
    val BillNo: String?,
    val BusinessActivityDescription: String?,
    val BusinessID: String?,
    val BusinessName: String?,
    val ContactPersonName: String?,
    val DateIssued: String?,
    val EndDate: String?,
    val PINNumber: String?,
    val POBox: String?,
    val PhysicalAddress: String?,
    val PlotNumber: String?,
    val PremisesArea: String?,
    val ReceiptNo: String?,
    val SBPFee: String?,
    val StartDate: String?,
    val Status: String?,
    val Telephone1: String?,
    val Town: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ActivityCode)
        parcel.writeString(ActivityDescription)
        parcel.writeString(AmountBilled)
        parcel.writeString(AmountPaid)
        parcel.writeString(BillNo)
        parcel.writeString(BusinessActivityDescription)
        parcel.writeString(BusinessID)
        parcel.writeString(BusinessName)
        parcel.writeString(ContactPersonName)
        parcel.writeString(DateIssued)
        parcel.writeString(EndDate)
        parcel.writeString(PINNumber)
        parcel.writeString(POBox)
        parcel.writeString(PhysicalAddress)
        parcel.writeString(PlotNumber)
        parcel.writeString(PremisesArea)
        parcel.writeString(ReceiptNo)
        parcel.writeString(SBPFee)
        parcel.writeString(StartDate)
        parcel.writeString(Status)
        parcel.writeString(Telephone1)
        parcel.writeString(Town)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BusinessesDataModel> {
        override fun createFromParcel(parcel: Parcel): BusinessesDataModel {
            return BusinessesDataModel(parcel)
        }

        override fun newArray(size: Int): Array<BusinessesDataModel?> {
            return arrayOfNulls(size)
        }
    }
}
