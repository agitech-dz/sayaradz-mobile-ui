package com.example.sayaradz_mobile.data

data class Offer(
    val id:String = "",
    var ad:String,
    var offredPrice:String,
    var automobilist:String,
    var automobilist_userName: String = "",
    var date: String = "",
    var isAccepted: Boolean = false
)