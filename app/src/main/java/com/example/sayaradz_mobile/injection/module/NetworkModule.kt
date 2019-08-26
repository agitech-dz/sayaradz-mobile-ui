package com.example.sayaradz_mobile.injection.module

import android.util.Log
import com.example.sayaradz_mobile.BuildConfig
import com.example.sayaradz_mobile.network.AdApi
import com.example.sayaradz_mobile.network.ManufacturerApi
import com.example.sayaradz_mobile.network.ModelApi
import com.example.sayaradz_mobile.network.VersionApi
import com.example.sayaradz_mobile.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Ad service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Ad service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideAdApi(retrofit: Retrofit): AdApi {
        Log.e("retrofiut", "retrofit")
        return retrofit.create(AdApi::class.java)
    }

    /**
     * Provides the Manufacturer service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Manufacturer service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideManufacturerApi(retrofit: Retrofit): ManufacturerApi {
        Log.e("retrofiut", "retrofit")
        return retrofit.create(ManufacturerApi::class.java)
    }


    /**
     * Provides the Model service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Model service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideModelApi(retrofit: Retrofit): ModelApi {
        return retrofit.create(ModelApi::class.java)
    }

    /**
     * Provides the Version service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Version service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideVersionApi(retrofit: Retrofit): VersionApi {
        return retrofit.create(VersionApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(getHttpLogClient())
            .build()
    }

    private val REQUEST_TIMEOUT = 6000

    private var logging = HttpLoggingInterceptor()

    private fun getHttpLogClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }
}