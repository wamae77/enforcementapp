package com.example.enforcmemntapp.data.source.remote.models

data class History(
    val allbusinesses: List<Allbusinesse>,
    val allparkingquery: List<Allparkingquery>,
    val allreceipts: List<Allreceipt>,
    val clampedparking: List<Clampedparking>,
    val invalidbusinesses: List<Any>,
    val invalidparking: List<Invalidparking>,
    val invalidreceipts: List<Invalidreceipt>,
    val validbusinesses: List<Validbusinesse>,
    val validparking: List<Validparking>,
    val validreceipts: List<Any>
)