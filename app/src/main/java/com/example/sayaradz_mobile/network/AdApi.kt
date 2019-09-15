package com.example.sayaradz_mobile.network

import com.example.sayaradz_mobile.data.Ad
import com.example.sayaradz_mobile.data.AdPost
import com.example.sayaradz_mobile.data.Offer
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface AdApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/api/automobilist/ads-filter?ordering=-date")
    fun getAds(): Observable<List<Ad>>


    /**
     * Post a new Ad
     */
    @Headers("Content-Type: application/json")
    @POST("/api/automobilist/ads/")
    fun postAd(@Body body: AdPost): Observable<Ad>

    /**
     * Search a new Ad
     */
    @GET("/api/automobilist/ads-filter")
    fun searchAds(@Query("search") search: String):  Observable<List<Ad>>


    /**
     * Post a new Offer
     */
    @Headers("Content-Type: application/json")
    @POST("/api/automobilist/post-offer")
    fun postOffer(@Body body: Offer): Observable<Offer>

    /**
     * Post a new Offer Photo
     */
    @POST("/api/automobilist/post-offer")
    fun postOfferPhoto(@Part("photo1") photo1: MultipartBody.Part): Observable<Offer>


}