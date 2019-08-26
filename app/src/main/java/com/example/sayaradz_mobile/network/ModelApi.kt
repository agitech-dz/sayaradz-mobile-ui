package com.example.sayaradz_mobile.network

import com.example.sayaradz_mobile.data.Model
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ModelApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/api/automobilist/{manufacturer}/models")
    fun getModels(@Path("manufacturer") manufacturer: String): Observable<List<Model>>
}