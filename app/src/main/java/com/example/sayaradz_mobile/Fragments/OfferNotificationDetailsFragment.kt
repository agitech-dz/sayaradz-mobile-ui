package com.example.sayaradz_mobile.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sayaradz_mobile.R

import androidx.databinding.DataBindingUtil
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.sayaradz_mobile.databinding.FragmentOfferNotificationDetailsBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory
import kotlinx.android.synthetic.main.fragment_ad_details.*


class OfferNotificationDetailsFragment : Fragment() {

    companion object {
        val instance = OfferNotificationDetailsFragment()
    }

    val args:OfferNotificationDetailsFragmentArgs by navArgs()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOfferNotificationDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_offer_notification_details,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrls = arrayListOf(
            args.notification.photo
        )
        activity?.findViewById<TextView>(R.id.notificationTitle)!!.text = args.notification.title
        activity?.findViewById<TextView>(R.id.notificationDescription)!!.text = args.notification.description
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE


        image_slider?.adapter = SliderAdapter(
            view!!.context,
            PicassoImageLoaderFactory(),
            imageUrls = imageUrls
        )
    }

}
