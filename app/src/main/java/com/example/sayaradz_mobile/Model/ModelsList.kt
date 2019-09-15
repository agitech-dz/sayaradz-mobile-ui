package com.example.sayaradz_mobile.Model

import com.google.gson.annotations.SerializedName

class ModelsList {

    @SerializedName("results")
    private val models:MutableList<Model>? = mutableListOf()
    fun getmodels ()
            : MutableList<Model>? {
        return models
    }
}