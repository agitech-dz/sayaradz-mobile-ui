package com.example.sayaradz_mobile.viewmodel


import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.sayaradz_mobile.Adapters.AdListAdapter
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.base.BaseViewModel
import com.example.sayaradz_mobile.data.Ad
import com.example.sayaradz_mobile.data.Manufacturer
import com.example.sayaradz_mobile.network.ManufacturerApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AdSpinnersViewModel: BaseViewModel(){

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadManufacturers() }
    @Inject
    lateinit var manufacturerApi: ManufacturerApi

    val manufacturerEntries: ObservableField<List<Manufacturer>> = ObservableField()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var selectedManufacturer: ObservableField<Manufacturer> = ObservableField()

    private lateinit var subscription: Disposable

    init{
        loadManufacturers()
    }

    private fun loadManufacturers(){
        subscription = manufacturerApi.getManufacturers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveManufacturersListStart() }
            .doOnTerminate { onRetrieveManufacturersListFinish() }
            .subscribe(
                // Add result
                { result -> onRetrieveManufacturersListSuccess(result) },
                { error -> onRetrieveManufacturersListError(error) }
            )
    }

    private fun onRetrieveManufacturersListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveManufacturersListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveManufacturersListSuccess(manufacturersList:List<Manufacturer>){
        manufacturerEntries.set(manufacturersList)
    }

    private fun onRetrieveManufacturersListError(error: Throwable){
        Log.e("throwable", error.localizedMessage)
        errorMessage.value = R.string.general_info
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}
