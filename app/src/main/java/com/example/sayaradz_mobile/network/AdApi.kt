package com.example.sayaradz_mobile.network

import com.example.sayaradz_mobile.data.Ad
import retrofit2.http.GET
import io.reactivex.Observable

interface AdApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/api/automobilist/ads/")
    fun getAds(): Observable<List<Ad>>
}