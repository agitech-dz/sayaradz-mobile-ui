package com.example.sayaradz_mobile.Data

import com.google.gson.annotations.SerializedName

class OptionModel (val id: String,
                   val name: String,
                   val model: String,
                   @SerializedName("model_name") val modelName: String,
                   val manufacturer: Int,
                   @SerializedName("tarif_id") val tarifId: Int,
                   @SerializedName("tarif_price") val tarifPrice: Long)