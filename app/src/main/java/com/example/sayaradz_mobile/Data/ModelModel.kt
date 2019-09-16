package com.example.sayaradz_mobile.Data

import com.google.gson.annotations.SerializedName

class ModelModel (val id: String,
                  val name: String,
                  val manufacturer: Int,
                  @SerializedName("manufacturer_name") val manufacturerName: String,
                  val image: String)