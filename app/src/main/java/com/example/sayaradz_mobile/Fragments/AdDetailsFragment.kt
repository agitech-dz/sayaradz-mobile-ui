package com.example.sayaradz_mobile.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sayaradz_mobile.R

import androidx.databinding.DataBindingUtil
import com.example.sayaradz_mobile.data.Ad
import com.example.sayaradz_mobile.databinding.FragmentAdDetailsBinding
import com.example.sayaradz_mobile.databinding.FragmentAdsBinding
import com.example.sayaradz_mobile.viewmodel.AdViewModel
import com.example.sayaradz_mobile.viewmodel.AdsListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory
import kotlinx.android.synthetic.main.fragment_ad_details.*


class AdDetailsFragment : Fragment() {

    companion object {
        val instance = AdDetailsFragment()
    }

    private lateinit var binding: FragmentAdDetailsBinding
    private lateinit var viewModel: AdViewModel
    private var errorSnackbar: Snackbar? = null
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_ad_details,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ad = Ad(
            AdDetailsFragmentArgs.fromBundle(arguments!!).adId,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adModel,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adVersion,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adVersionName,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adManufacturer,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adManufacturerName,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adYear,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adDistance,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adDescription,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adAutomobilist,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adAutomobilistUserName,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adAutomobilistAddress,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adDate,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adMinPrice,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adPhoto1,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adPhoto2,
            AdDetailsFragmentArgs.fromBundle(arguments!!).adPhoto3
        )
        val imageUrls = arrayListOf(
            "https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85",
            "https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85",
            "https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85"
        )
        //var adId = com.example.sayaradz_mobile.Fragments.AdDetailsFragmentArgs.fromBundle(arguments!!).adId
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE


        image_slider?.adapter = SliderAdapter(
            view!!.context,
            PicassoImageLoaderFactory(),
            imageUrls = imageUrls
        )

        adBrand.text = ad.manufacturer_name
        adModelAndVersion.text = ad.model + " " + ad.version_name
        adYear.text = ad.year
        adKm.text = ad.distance + " km"
        //adEnergy.text
        //adPersonsNumber.text
        adDescription.text = ad.description
        adMinPrice.text = ad.minPrice + " DA"
    }


}
