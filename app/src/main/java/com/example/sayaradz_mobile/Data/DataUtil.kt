package com.example.sayaradz_mobile.Data


import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.util.Log
import com.example.sayaradz.Data.ManufactorList
import com.example.sayaradz.Data.Manufactors
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.example.sayaradz.Model.RestService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataUtil {



    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }


    fun getRetrofit(context: Context) : Retrofit {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
        Log.d("Markkkkk ","here you are hhh")
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://sayaradz-back-end.herokuapp.com/api/")
            .client(okHttpClient)
            .build()



    }


    fun getManufactors( numPage: Int, nbrEle: Int, service: RestService):  ArrayList<String>{

        var marks: ArrayList<String>? = arrayListOf()

        val call = service.ListMarque(numPage, nbrEle)
        call.enqueue(object : Callback<MutableList<Manufactors>> {
            override fun onFailure(call: Call<MutableList<Manufactors>>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty " + t.message)
            }

            override fun onResponse(call: retrofit2.Call<MutableList<Manufactors>>, response: Response<MutableList<Manufactors>>) {
                if (response.code() == 200) {
                    Log.d("Tmarque ", "you've got it"+response.body().toString())
                    // Thread.sleep(40000)
                    var MarqueList = response.body()
                    Log.d("Tmarque ","here you are "+MarqueList!![0].name)
                    if (MarqueList != null) {
                        var j =0
                        for(i in MarqueList ){
                            marks!!.add( i.name)


                        }

                        Log.d("Markkkkk ","here you are hhh"+marks!!.get(0))

                    }
                }
            }
        })
        return marks!!
    }


}