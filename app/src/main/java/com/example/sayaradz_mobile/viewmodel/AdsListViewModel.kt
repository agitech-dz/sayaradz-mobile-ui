package com.example.sayaradz_mobile.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.sayaradz_mobile.Adapters.AdListAdapter
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.base.BaseViewModel
import com.example.sayaradz_mobile.data.Ad
import com.example.sayaradz_mobile.network.AdApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AdsListViewModel: BaseViewModel(){

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {  }
    @Inject
    lateinit var adApi: AdApi

    val adsListAdapter: AdListAdapter = AdListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        loadAds()
    }


    fun searchAds(search: String){
        subscription = adApi.searchAds(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveAdsListStart() }
            .doOnTerminate { onRetrieveAdsListFinish() }
            .subscribe(
                // Add result
                { result -> onRetrieveAdsListSuccess(result) },
                { error -> onRetrieveAdsListError(error) }
            )
    }

    fun loadAds(){
        subscription = adApi.getAds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveAdsListStart() }
            .doOnTerminate { onRetrieveAdsListFinish() }
            .subscribe(
                // Add result
                { result -> onRetrieveAdsListSuccess(result) },
                { error -> onRetrieveAdsListError(error) }
            )
    }

    private fun onRetrieveAdsListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveAdsListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveAdsListSuccess(adsList:List<Ad>){
        adsListAdapter.updatePostList(adsList)
    }

    private fun onRetrieveAdsListError(error: Throwable){
        Log.e("throwable", error.localizedMessage)
        errorMessage.value = R.string.general_info
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}