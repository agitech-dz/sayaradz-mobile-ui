package com.example.sayaradz_mobile.HttpRequests

import com.example.sayaradz_mobile.Model.AuthResponse
import com.example.sayaradz_mobile.Model.Automobilist
import com.example.sayaradz_mobile.Model.CommandNotification
import com.example.sayaradz_mobile.Model.NotificationBody
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

}