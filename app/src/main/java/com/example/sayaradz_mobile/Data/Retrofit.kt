package com.example.sayaradz.Model

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
fun getRetrofit() : Retrofit{
   return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://sayaradz-back-end.herokuapp.com/api/")
        .build()
}

}