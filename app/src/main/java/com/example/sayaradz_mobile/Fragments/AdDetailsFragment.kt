package com.example.sayaradz_mobile.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sayaradz_mobile.R

import androidx.databinding.DataBindingUtil
import com.example.sayaradz_mobile.databinding.FragmentAdDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory
import kotlinx.android.synthetic.main.fragment_ad_details.*


class AdDetailsFragment : Fragment() {



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAdDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_ad_details,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }

}
