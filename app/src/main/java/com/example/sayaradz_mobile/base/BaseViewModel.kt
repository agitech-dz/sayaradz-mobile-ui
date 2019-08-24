package com.example.sayaradz_mobile.base

import androidx.lifecycle.ViewModel
import com.example.sayaradz_mobile.injection.component.DaggerViewModelInjector
import com.example.sayaradz_mobile.injection.component.ViewModelInjector
import com.example.sayaradz_mobile.injection.module.NetworkModule
import com.example.sayaradz_mobile.viewmodel.AdsListViewModel

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is AdsListViewModel -> injector.inject(this)
        }
    }
}