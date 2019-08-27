package com.example.sayaradz_mobile.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.base.BaseViewModel
import com.example.sayaradz_mobile.data.Ad
import com.example.sayaradz_mobile.data.AdPost
import com.example.sayaradz_mobile.data.Offer
import com.example.sayaradz_mobile.network.AdApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AdViewModel: BaseViewModel() {

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {  }
    @Inject
    lateinit var adApi: AdApi
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val buttonsVisibility: MutableLiveData<Int> = MutableLiveData()

    var newAd: MutableLiveData<Ad> = MutableLiveData()
    private lateinit var subscription: Disposable

    fun postAd(ad: Ad){
        subscription = adApi.postAd(AdPost(
            ad.model,
            ad.version,
            ad.manufacturer,
            ad.year,
            ad.distance,
            ad.description,
            ad.automobilist,
            ad.minPrice
        ))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onPostAdStart() }
            .doOnTerminate { onPostAdFinish() }
            .subscribe(
                // Add result
                { result -> onPostAdSuccess(result) },
                { error -> onPostAdError(error) }
            )
    }

    private fun onPostAdStart(){
        loadingVisibility.value = View.VISIBLE
        buttonsVisibility.value = View.GONE
        errorMessage.value = null
    }

    private fun onPostAdFinish(){
        loadingVisibility.value = View.GONE

    }

    private fun onPostAdSuccess(ad :Ad){
        buttonsVisibility.value = View.VISIBLE
        newAd.value = ad
    }


    private fun onPostAdError(error: Throwable){
        errorMessage.value = R.string.general_info
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


    private val adId = MutableLiveData<String>()
    private val adManufacturer = MutableLiveData<String>()
    private val adModel = MutableLiveData<String>()
    private val adVersion = MutableLiveData<String>()
    private val adDescription = MutableLiveData<String>()
    private val adDistance = MutableLiveData<String>()
    private val adDate = MutableLiveData<String>()
    private val adMinPrice = MutableLiveData<String>()
    private val adPhoto1 = MutableLiveData<String>()
    private val adPhoto2 = MutableLiveData<String>()
    private val adPhoto3 = MutableLiveData<String>()
    private val adAutomobilistUserName = MutableLiveData<String>()
    private val adAutomobilistAddress = MutableLiveData<String>()
    private val adYear = MutableLiveData<String>()
    private val adYearAndDistance = MutableLiveData<String>()
    private val adModelAndVersion = MutableLiveData<String>()

    fun bind(ad: Ad){
        adId.value = ad.id
        adManufacturer.value = ad.manufacturer_name
        adModel.value = ad.model
        adVersion.value = ad.version_name
        adDescription.value = ad.description
        adDistance.value = ad.distance + " km"
        adDate.value = ad.date
        adMinPrice.value = ad.minPrice + " DA"
        adPhoto1.value = ad.photo1
        adPhoto2.value = ad.photo2
        adPhoto3.value = ad.photo3
        adAutomobilistUserName.value = ad.automobilist_username
        adAutomobilistAddress.value = ad.automobilist_address
        adYear.value = ad.year
        adYearAndDistance.value = ad.year + " | " + ad.distance + " km"
        adModelAndVersion.value = ad.model + " " + ad.version_name
    }

    fun getAdId():MutableLiveData<String>{
        return adManufacturer
    }

    fun getAdManufacturer():MutableLiveData<String>{
        return adManufacturer
    }

    fun getAdModel():MutableLiveData<String>{
        return adModel
    }

    fun getAdVerion():MutableLiveData<String>{
        return adVersion
    }

    fun getAdDescription():MutableLiveData<String>{
        return adDescription
    }

    fun getAdPhoto1():MutableLiveData<String>{
        return adPhoto1
    }

    fun getAdPhoto2():MutableLiveData<String>{
        return adPhoto2
    }

    fun getAdPhoto3():MutableLiveData<String>{
        return adPhoto3
    }

    fun getAdMinPrice():MutableLiveData<String>{
        return adMinPrice
    }

    fun getAdYearAndDistance():MutableLiveData<String>{
        return adYearAndDistance
    }

    fun getAdModelAndVersion():MutableLiveData<String>{
        return adModelAndVersion
    }

    fun getAdDistance():MutableLiveData<String>{
        return adDistance
    }

    fun getAdDate():MutableLiveData<String>{
        return adDate
    }

    fun getAdYear():MutableLiveData<String>{
        return adYear
    }


    fun getAdAutomobilistUserName():MutableLiveData<String>{
        return adAutomobilistUserName
    }

    fun getAdAutomobilistAddress():MutableLiveData<String>{
        return adAutomobilistAddress
    }


    fun postOffer(automobilist: String, ad: String, offeredPrice: String){
        subscription = adApi.postOffer(Offer(
            offredPrice = offeredPrice,
            automobilist = automobilist,
            ad = ad
        ))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onPostOfferStart() }
            .doOnTerminate { onPostOfferFinish() }
            .subscribe(
                // Add result
                { result -> onPostOfferSuccess(result) },
                { error -> onPostOfferError(error) }
            )
    }


    private fun onPostOfferStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onPostOfferFinish(){
        loadingVisibility.value = View.GONE

    }

    private fun onPostOfferSuccess(offer :Offer){
        buttonsVisibility.value = View.VISIBLE
    }


    private fun onPostOfferError(error: Throwable){
        errorMessage.value = R.string.general_info
    }



}