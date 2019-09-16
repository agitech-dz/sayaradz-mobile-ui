package com.example.sayaradz_mobile.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sayaradz_mobile.R

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sayaradz_mobile.HttpRequests.RestService
import com.example.sayaradz_mobile.HttpRequests.Retrofit
import com.example.sayaradz_mobile.Model.Notification
import com.example.sayaradz_mobile.Utilities.Utilities
import com.example.sayaradz_mobile.databinding.FragmentPostOfferNotificationBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_ad_details.notificationDescription
import kotlinx.android.synthetic.main.fragment_ad_details.notificationTitle
import kotlinx.android.synthetic.main.fragment_post_offer_notification.*


class PostOfferNotificationFragment : Fragment() {

    val args:PostOfferNotificationFragmentArgs by navArgs()
    private lateinit var notification:Notification

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notification = args.notification
        val binding: FragmentPostOfferNotificationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_offer_notification,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationTitle.text = notification.title
        notificationDescription.text = args.notification.description
        offerId.text = notification.body!!.id.toString()
        actorName.text = notification.body!!.actorUserName
        actorEmail.text = notification.body!!.actorEmail
        offerTotal.text = notification.body!!.verb + " DZD"
        offerDate.text = notification.date
        Glide.with(this).load(notification.photo).into(notificationImage)

        acceptOfferButton.setOnClickListener(this::onAcceptClick)



        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE


    }

    fun onAcceptClick(view: View){
        if (Utilities.hasNetwork(context!!)){
            val compositeDisposable = CompositeDisposable()
            val restService = Retrofit.getRetrofit().create(RestService::class.java)
            compositeDisposable.add(restService.acceptOffer(notification.body!!.actionObject.toInt(),notification.body!!.actorTarget.toInt(),
                notification.body!!.verb.toDouble().toInt(),notification.body!!.recipient,true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError))

        }else{
            Toast.makeText(context,"Connexion Internet Impossible", Toast.LENGTH_LONG).show()

        }

    }
    private fun handleResponse(void: Void){
        Toast.makeText(context,"Offre accepté avec succès !", Toast.LENGTH_LONG).show()
        view!!.findNavController().navigateUp()

    }
    private fun handleError(t:Throwable){
        Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()

    }
    override fun onDestroy() {
        super.onDestroy()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE
    }

}
