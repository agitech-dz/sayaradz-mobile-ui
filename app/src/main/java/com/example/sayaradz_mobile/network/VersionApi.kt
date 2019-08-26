package com.example.sayaradz_mobile.network

import com.example.sayaradz_mobile.data.Version
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

interface VersionApi {
    /**
     * Get the list of the versions from the API
     */
    @GET("/api/automobilist/{model}/versions")
    fun getVersions(@Path("model") model: String): Observable<List<Version>>
}