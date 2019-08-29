package com.example.sayaradz.Data

import com.google.gson.annotations.SerializedName



class ManufactorList {
    @SerializedName("results")
    private var models: MutableList<Manufactors> = mutableListOf()
    fun getmodels ()
    : MutableList<Manufactors> {
        return models
    }
}