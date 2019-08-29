package com.example.sayaradz.Data

import com.google.gson.annotations.SerializedName

class FollowedModelList {
    @SerializedName("results")
    private val Followedmodels:MutableList<FollowedModel>? = mutableListOf()
    fun getmodels ()
            : MutableList<FollowedModel>? {
        return Followedmodels
    }
}