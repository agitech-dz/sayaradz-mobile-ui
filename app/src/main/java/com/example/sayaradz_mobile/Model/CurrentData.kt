package com.example.sayaradz_mobile.Model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.sayaradz_mobile.Data.BrandModel
import com.example.sayaradz_mobile.Data.ColorModel
import com.example.sayaradz_mobile.Data.ModelModel
import com.example.sayaradz_mobile.Data.VersionModel

class CurrentData : ViewModel() {

    val currentBrand : MutableLiveData<BrandModel?> = MutableLiveData()
    val currentModel : MutableLiveData<ModelModel?> = MutableLiveData()
    val currentVersion : MutableLiveData<VersionModel?> = MutableLiveData()
    val currentColor : MutableLiveData<ColorModel?> = MutableLiveData()
    val price : MutableLiveData<Long?> = MutableLiveData()

    val versionPrice : MutableLiveData<Long?> = MutableLiveData()
    val colorPrice : MutableLiveData<Long?> = MutableLiveData()
    val optionsPrice : MutableLiveData<Long?> = MutableLiveData()

    init {
        setVersionPrice(0)
        setColorPrice(0)
        setOptionsPrice(0)
    }

    fun setBrand(brand : BrandModel?) {
        currentBrand.value = brand
    }

    fun setModel(model : ModelModel?) {
        currentModel.value = model
    }

    fun setVersion(version: VersionModel?) {
        currentVersion.value = version
    }

    fun setColor(color: ColorModel?) {
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