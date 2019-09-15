package com.example.sayaradz_mobile.Model

import com.google.gson.annotations.SerializedName



class ManufacturersList {
    @SerializedName("results")
    private var models: MutableList<Manufacturer> = mutableListOf()
    fun getmodels ()
            : MutableList<Manufacturer> {
        return models
    }
}