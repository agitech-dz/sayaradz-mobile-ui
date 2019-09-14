package com.example.sayaradz_mobile.HttpRequests

import com.example.sayaradz_mobile.Model.CommandNotification
import com.example.sayaradz_mobile.Model.NotificationBody
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestService {

    @GET("automobilist/notifications/{recipient}")
    fun getNotifications(
        @Path("recipient") recipient: Int
    ) : Observable<List<NotificationBody>>



    @PUT("/api/automobilist/accept-offer/{id}")
    fun acceptOffer(
        @Path("id") id : Int
    ) : Observable<Void>
}