package com.example.sayaradz_mobile.HttpRequests

import com.example.sayaradz_mobile.Model.*
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface RestService {

    @GET("automobilists/{id}")
    fun getAutomobilist(
        @Path("id") id: Int
    ):Observable<Automobilist>

    @GET("automobilist/notifications/{recipient}")
    fun getNotifications(
        @Path("recipient") recipient: Int
    ) : Observable<List<NotificationBody>>


    @POST("automobilist/google_login/")
    fun googleLogin(
        @Body accessToken : JsonObject
    ): Observable<AuthResponse>


    @POST("automobilist/facebook_login/")
    fun facebookLogin(
        @Body accessToken : JsonObject
    ):Observable<AuthResponse>

    @PUT("automobilist/notifications/{id}")
    fun setNotificationAsRead(
        @Path("id") id: Int
    ) : Observable<Void>


    @PUT("automobilist/accept-offer/{id}")
    fun acceptOffer(
        @Path("id") id : Int,
        @Body ad : Int,
        @Body offeredPrice : Int,
        @Body automobilist : Int,
        @Body isAccepted : Boolean
    ) : Observable<Void>

    //Home


    @GET("automobilist/manufacturers")
    fun ListMarque(
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Call<MutableList<Manufacturer>>

    @GET("automobilist/manufacturers")
    fun getManufacturers(
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Observable<List<Manufacturer>>

    @GET("automobilist/{manufacturer}/models")
    fun ListModel(
        @Path("manufacturer") manufacturer : Int,
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Call<MutableList<Model>>


    @GET("automobilist/all-models")
    fun ListModel(
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Call<MutableList<Model>>

    @GET("automobilist/all-models")
    fun getModels(
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Observable<List<Model>>

    @GET("automobilist/all-versions")
    fun ListVersion(
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Call<MutableList<Version>>

    @GET("automobilist/all-versions")
    fun getVersions(
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Observable<List<Version>>


    @GET("automobilist/{model}/versions")
    fun ListVersion(
        @Path("model") model : String,
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Call<MutableList<Version>>




}