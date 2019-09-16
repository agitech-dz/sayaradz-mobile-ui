package com.example.sayaradz_mobile.Data

import com.google.gson.annotations.SerializedName

class VersionModel (val id: String,
                    val name: String,
                    val options: List<String>,
                    val model: String,
                    val image: String,
                    @SerializedName("tarif_id") val tarifId: Int,
                    @SerializedName("tarif_price") val tarifPrice: Long)