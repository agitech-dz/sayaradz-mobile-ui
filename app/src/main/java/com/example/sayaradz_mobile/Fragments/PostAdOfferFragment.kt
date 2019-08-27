package com.example.sayaradz_mobile.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.databinding.FragmentPostAdOfferBinding
import com.example.sayaradz_mobile.viewmodel.AdViewModel
import com.example.sayaradz_mobile.viewmodel.AdsListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_post_ad_offer.*

class PostAdOfferFragment : Fragment() {

    private lateinit var binding: FragmentPostAdOfferBinding
    private lateinit var viewModel: AdViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_ad_offer,
            container,
            false)

        viewModel = ViewModelProviders.of(this).get(AdViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.viewModel = viewModel

        return binding.root
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction("Retry", viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        post_ad_offer.text = post_ad_offer.text.toString() + ""+ PostAdOfferFragmentArgs.fromBundle(arguments!!).minPrice

        makeOfferAction.setOnClickListener{
            viewModel.postOffer(
                "2",
                PostAdOfferFragmentArgs.fromBundle(arguments!!).adId,
                offeredPrice.text.toString())

            viewModel.errorMessage.observe(this, Observer {
                    errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
            })
            var action = PostAdOfferFragmentDirections.actionPostAdOfferToAdsFragment()
            it.findNavController().navigate(action)
        }


    }


}
