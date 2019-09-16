package com.example.sayaradz_mobile.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sayaradz_mobile.R

import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sayaradz_mobile.Model.Notification
import com.example.sayaradz_mobile.databinding.FragmentAcceptOfferNotificationBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory
import kotlinx.android.synthetic.main.fragment_ad_details.image_slider
import kotlinx.android.synthetic.main.fragment_ad_details.notificationDescription
import kotlinx.android.synthetic.main.fragment_ad_details.notificationTitle
import kotlinx.android.synthetic.main.fragment_post_offer_notification.*


class AcceptOfferNotificationFragment : Fragment() {

    val args:AcceptOfferNotificationFragmentArgs by navArgs()
    private lateinit var notification:Notification

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notification = args.notification
        val binding: FragmentAcceptOfferNotificationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_accept_offer_notification,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationTitle.text = args.notification.title
        notificationDescription.text = args.notification.description
        offerId.text = notification.body!!.actorTarget
        actorName.text = notification.body!!.actorUserName
        offerTotal.text = notification.body!!.verb + " DZD"
        offerDate.text = notification.date
        Glide.with(this).load(notification.photo).into(notificationImage)

        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE

    }
    override fun onDestroy() {
        super.onDestroy()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE
    }

}