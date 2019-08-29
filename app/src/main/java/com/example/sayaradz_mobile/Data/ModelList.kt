package com.example.sayaradz.Data

import com.google.gson.annotations.SerializedName

class ModelList {

    @SerializedName("results")
    private val models:MutableList<Models>? = mutableListOf()
    fun getmodels ()
            : MutableList<Models>? {
        return models
    }
}