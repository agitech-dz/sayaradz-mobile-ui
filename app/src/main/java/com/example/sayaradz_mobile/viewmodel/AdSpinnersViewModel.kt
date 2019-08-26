package com.example.sayaradz_mobile.viewmodel


import android.content.Context
import android.util.Log
import android.view.Display
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.sayaradz_mobile.Adapters.AdListAdapter
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.base.BaseViewModel
import com.example.sayaradz_mobile.data.Ad
import com.example.sayaradz_mobile.data.Manufacturer
import com.example.sayaradz_mobile.data.Model
import com.example.sayaradz_mobile.network.ManufacturerApi
import com.example.sayaradz_mobile.network.ModelApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AdSpinnersViewModel: BaseViewModel(){

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadManufacturers() }
    @Inject
    lateinit var manufacturerApi: ManufacturerApi

    @Inject
    lateinit var modelApi: ModelApi

    val manufacturerEntries: ObservableField<List<Manufacturer>> = ObservableField()

    val modelEntries: ObservableField<List<Model>> = ObservableField()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable


     fun loadManufacturers(){
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

    fun loadModels(manufacturer: String){
        subscription = modelApi.getModels(manufacturer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveModelsListStart() }
            .doOnTerminate { onRetrieveModelsListFinish() }
            .subscribe(
                // Add result
                { result -> onRetrieveModelsListSuccess(result) },
                { error -> onRetrieveModelsListError(error) }
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

    private fun onRetrieveModelsListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveModelsListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveModelsListSuccess(modelsList:List<Model>){
        modelEntries.set(modelsList)
    }

    private fun onRetrieveModelsListError(error: Throwable){
        errorMessage.value = R.string.loading_data_error
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}
