package com.example.sayaradz_mobile.data

import android.R.string



data class Manufacturer(
    val id:String,
    var name:String,
    var nationality:String
){
    override fun toString(): String {
        return name
    }
}