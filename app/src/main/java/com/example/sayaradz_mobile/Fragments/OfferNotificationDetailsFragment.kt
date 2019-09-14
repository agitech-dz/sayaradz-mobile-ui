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
import com.bumptech.glide.Glide
import com.example.sayaradz_mobile.Model.Notification
import com.example.sayaradz_mobile.databinding.FragmentOfferNotificationDetailsBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory
import kotlinx.android.synthetic.main.fragment_ad_details.*
import kotlinx.android.synthetic.main.fragment_ad_details.image_slider
import kotlinx.android.synthetic.main.fragment_ad_details.notificationDescription
import kotlinx.android.synthetic.main.fragment_ad_details.notificationTitle
import kotlinx.android.synthetic.main.fragment_offer_notification_details.*


class OfferNotificationDetailsFragment : Fragment() {

    companion object {
        val instance = OfferNotificationDetailsFragment()
    }

    val args:OfferNotificationDetailsFragmentArgs by navArgs()
    private lateinit var notification:Notification

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notification = args.notification
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
        notificationTitle.text = args.notification.title
        notificationDescription.text = args.notification.description
        offerId.text = notification.body!!.id.toString()
        actorName.text = notification.body!!.actorUserName
        actorEmail.text = notification.body!!.actorEmail
        actorTel.text = notification.body!!.actorTelephone
        offerTotal.text = notification.body!!.verb + " DZD"
        offerDate.text = notification.date
        Glide.with(this).load(notification.body!!.image).into(notificationImage)

        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE


        image_slider?.adapter = SliderAdapter(
            view!!.context,
            PicassoImageLoaderFactory(),
            imageUrls = imageUrls
        )
    }
    override fun onDestroy() {
        super.onDestroy()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE
    }

}
