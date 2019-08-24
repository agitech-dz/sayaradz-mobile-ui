package com.example.sayaradz_mobile.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.sayaradz_mobile.base.BaseViewModel
import com.example.sayaradz_mobile.data.Ad

class AdViewModel: BaseViewModel() {
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


}