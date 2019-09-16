package com.example.sayaradz_mobile.Interface

import com.example.sayaradz_mobile.Data.*
import com.example.sayaradz_mobile.Model.SearchInput
import com.example.sayaradz_mobile.Model.SearchOutput
import com.example.sayaradz_mobile.Model.Transaction
import io.reactivex.Observable
import retrofit2.http.*

interface GetData {

    // Get the list of all Brands
    @GET("api/automobilist/manufacturers")
    fun getBrands(): Observable<List<BrandModel>>

    // Get the list of the Models according to a Brand given
    @GET("api/automobilist/{manufacturer}/models")
    fun getModels(
            @Path("manufacturer")
            manufacturer: Int) : Observable<List<ModelModel>>

    // Get the list of the Versions according to a Model given
    @GET("api/automobilist/{model}/versions")
    fun getVersions(
            @Path("model")
            model: String): Observable<List<VersionModel>>

    // Get the list of all Colors
    @GET("api/automobilist/colors")
    fun getColors(): Observable<List<ColorModel>>

    // Get the list of all Options
    @GET("api/automobilist/options")
    fun getOptions(): Observable<List<OptionModel>>

    // Search the vehicle in the Supply
    @Headers("Content-Type: application/json")
    @POST("api/automobilist/compose-car-filter")
    fun searchVehicle(@Body body: SearchInput): Observable<SearchOutput>

    // Add a transaction
    @Headers("Content-Type: application/json")
    @POST("api/automobilist/transactions")
    fun addTransaction(@Body body: Transaction)

}