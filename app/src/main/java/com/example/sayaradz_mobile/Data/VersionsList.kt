package com.example.sayaradz.Data

import com.google.gson.annotations.SerializedName

class VersionsList {

    @SerializedName("results")
    private val version: MutableList<Version>? = mutableListOf()

    fun getVersion()
            : MutableList<Version>?{
        return version
    }
}