package com.example.sayaradz_mobile.HttpRequests

import com.example.sayaradz_mobile.Model.CommandNotification
import com.example.sayaradz_mobile.Model.OfferNotification
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestService {

    @GET("automobilist/offers-notifications/{recipient}")
    fun getOfferNotifications(
        @Path("recipient") recipient: Int
    ) : Observable<List<OfferNotification>>

    @GET("automobilist/commands-notifications/{recipient}")
    fun getCommandNotifications(
        @Path("recipient") recipient: Int
    ) : Observable<List<CommandNotification>>

    @PUT("/api/automobilist/accept-offer/{id}")
    fun acceptOffer(
        @Path("id") id : Int
    ) : Observable<Void>
}