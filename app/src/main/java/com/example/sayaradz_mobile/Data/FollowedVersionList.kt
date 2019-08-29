package com.example.sayaradz.Data

import com.google.gson.annotations.SerializedName

class FollowedVersionList {
    @SerializedName("results")
    private val FolloweVersions:MutableList<FollowedVersion>? = mutableListOf()
    fun getmodels ()
            : MutableList<FollowedVersion>? {
        return FolloweVersions
    }
}