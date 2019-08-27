package com.example.sayaradz_mobile.Model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class CurrentData : ViewModel() {

    val currentBrand : MutableLiveData<CarBrand?> = MutableLiveData()
    val currentModel : MutableLiveData<Model?> = MutableLiveData()
    val currentVersion : MutableLiveData<Version?> = MutableLiveData()
    val currentColor : MutableLiveData<Color?> = MutableLiveData()
    val price : MutableLiveData<Long?> = MutableLiveData()

    val versionPrice : MutableLiveData<Long?> = MutableLiveData()
    val colorPrice : MutableLiveData<Long?> = MutableLiveData()
    val optionsPrice : MutableLiveData<Long?> = MutableLiveData()

    fun setBrand(brand : CarBrand?) {
        currentBrand.value = brand
    }

    fun setModel(model : Model?) {
        currentModel.value = model
    }

    fun setVersion(version: Version?) {
        currentVersion.value = version
    }

    fun setColor(color: Color?) {
        currentColor.value = color
    }

    fun updatePrice() {
        var pr : Long? = 0
        if (pr != null && versionPrice.value != null) {
            pr = versionPrice.value?.plus(pr)
        }
        if (pr != null && colorPrice.value != null) {
            pr = colorPrice.value?.plus(pr)
        }
        if (pr != null && optionsPrice.value != null) {
            pr = optionsPrice.value?.plus(pr)
        }
        price.value = pr
    }

    fun setVersionPrice(price : Long?) {
        versionPrice.value = price
    }

    fun setColorPrice(price : Long?) {
        colorPrice.value = price
    }

    fun setOptionsPrice(price : Long?) {
        optionsPrice.value = price
    }

}