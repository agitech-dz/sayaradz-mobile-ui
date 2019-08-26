package com.example.sayaradz_mobile.data

data class Model(
    val id:String,
    var name:String,
    var manufacturer:String,
    var manufacturer_name:String
){
    override fun toString(): String {
        return name
    }
}