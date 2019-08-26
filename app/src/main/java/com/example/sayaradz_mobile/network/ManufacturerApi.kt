package com.example.sayaradz_mobile.network

import com.example.sayaradz_mobile.data.Manufacturer
import retrofit2.http.GET

import io.reactivex.Observable

interface ManufacturerApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/api/automobilist/manufacturers")
    fun getManufacturers(): Observable<List<Manufacturer>>
}