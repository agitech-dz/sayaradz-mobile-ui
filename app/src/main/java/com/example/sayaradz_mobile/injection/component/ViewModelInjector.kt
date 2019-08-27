package com.example.sayaradz_mobile.injection.component

import com.example.sayaradz_mobile.injection.module.NetworkModule
import com.example.sayaradz_mobile.viewmodel.AdSpinnersViewModel
import com.example.sayaradz_mobile.viewmodel.AdViewModel
import com.example.sayaradz_mobile.viewmodel.AdsListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified AdsListViewModel.
     * @param adListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(adsListViewModel: AdsListViewModel)

    fun inject(adSpinnersViewModel: AdSpinnersViewModel)

    fun inject(adViewModel: AdViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}