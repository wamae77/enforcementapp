package com.example.enforcmemntapp.data.source

import com.example.enforcmemntapp.data.source.local.entities.UserdataEntities
import com.example.enforcmemntapp.data.source.models.BusinessesDataModel
import com.example.enforcmemntapp.data.source.models.ParkingDataModel
import com.example.enforcmemntapp.data.source.remote.models.CheckbusinessResponse
import com.example.enforcmemntapp.data.source.remote.models.LoginResponse
import com.example.enforcmemntapp.data.source.remote.models.Parkingdata
import com.example.enforcmemntapp.data.source.remote.models.Userdata

fun LoginResponse.toUserData() = Userdata(
    Email = response_data.userdata.Email,
    FirstName = response_data.userdata.FirstName,
    IDNo = response_data.userdata.IDNo,
    LastName = response_data.userdata.LastName,
    OtherName = response_data.userdata.OtherName,
    UserID = response_data.userdata.UserID,
    password_set = response_data.userdata.password_set,
    phone = response_data.userdata.phone

)

fun Userdata.toUserDataEntities() = UserdataEntities(
    id = 0,
    Email, FirstName, IDNo, LastName, OtherName, UserID, password_set, phone

)

fun CheckbusinessResponse.toBusinessDataModel() = BusinessesDataModel(
    ActivityCode = response_data.businessesdata.ActivityCode,
    ActivityDescription = response_data.businessesdata.ActivityDescription,
    AmountBilled = response_data.businessesdata.AmountBilled,
    BillNo = response_data.businessesdata.BillNo,
    BusinessActivityDescription = response_data.businessesdata.BusinessActivityDescription,
    BusinessID = response_data.businessesdata.BusinessID,
    BusinessName = response_data.businessesdata.BusinessName,
    ContactPersonName = response_data.businessesdata.ContactPersonName,
    DateIssued = response_data.businessesdata.DateIssued,
    EndDate = response_data.businessesdata.EndDate,
    PINNumber = response_data.businessesdata.PINNumber,
    POBox = response_data.businessesdata.POBox,
    PhysicalAddress = response_data.businessesdata.PhysicalAddress,
    PlotNumber = response_data.businessesdata.PlotNumber,
    PremisesArea = response_data.businessesdata.PremisesArea,
    ReceiptNo = response_data.businessesdata.ReceiptNo,
    SBPFee = response_data.businessesdata.SBPFee,
    StartDate = response_data.businessesdata.StartDate,
    Status = response_data.businessesdata.Status,
    Telephone1 = response_data.businessesdata.Telephone1,
    Town = response_data.businessesdata.Town,
    AmountPaid = response_data.businessesdata.AmountPaid
)

fun Parkingdata.toParkingDetailsModel() = ParkingDataModel(
    AmountPaid,
    BillAmount,
    BillDate,
    Category,
    ClampedAmount,
    ClampedStatus,
    CurrentState,
    Description,
    Duration,
    EndDate,
    PaidDate,
    ParkingFee,
    SaccoName,
    StartDate,
    VehicleRegistration,
    VehicleType,
    zone
)