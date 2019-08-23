package com.example.sayaradz_mobile.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sayaradz_mobile.R

import androidx.databinding.DataBindingUtil

import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory


class AdDetailsFragment : Fragment() {

    companion object {
        val instance = AdDetailsFragment()
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ad_details, container, false)

        val imageUrls = arrayListOf(
            "https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85",
            "https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85",
            "https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85"
        )
        //var adId = com.example.sayaradz_mobile.Fragments.AdDetailsFragmentArgs.fromBundle(arguments!!).adId

        val imageSlider = container?.findViewById<ImageSlider>(R.id.image_slider)

        imageSlider?.adapter = SliderAdapter(
            container!!.context,
            PicassoImageLoaderFactory(),
            imageUrls = imageUrls
            )

        return view
    }

}
