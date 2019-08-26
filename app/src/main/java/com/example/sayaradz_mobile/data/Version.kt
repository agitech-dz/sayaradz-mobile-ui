package com.example.sayaradz_mobile.data

data class Version(
    val id:String,
    var name:String,
    var options: Array<String>,
    var model:String,
    var manufacturer_name:String,
    var tarif_id: String,
    var tarif_price: Float
){
    override fun toString(): String {
        return name
    }
}