package com.example.sayaradz_mobile.HttpRequests

import com.example.sayaradz_mobile.Model.CommandNotification
import com.example.sayaradz_mobile.Model.NotificationBody
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface RestService {

    @GET("automobilist/notifications/{recipient}")
    fun getNotifications(
        @Path("recipient") recipient: Int
    ) : Observable<List<NotificationBody>>

    @PUT("automobilist/notifications/{id}")
    fun setNotificationAsRead(
        @Path("id") id: Int
    ) : Observable<Void>


    @FormUrlEncoded
    @PUT("automobilist/accept-offer/{id}")
    fun acceptOffer(
        @Path("id") id : Int,
        @Field("ad") ad : Int,
        @Field("OfferedPrice") offeredPrice : Int,
        @Field("automobilist") automobilist : Int,
        @Field("isAccepted") isAccepted : Boolean
    ) : Observable<Void>

}