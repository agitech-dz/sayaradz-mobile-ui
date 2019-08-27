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
import com.example.sayaradz_mobile.data.Ad
import com.example.sayaradz_mobile.databinding.FragmentPostAdFinishBinding
import com.example.sayaradz_mobile.viewmodel.AdViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_post_ad_finish.*


class PostAdFinishFragment : Fragment() {

    private lateinit var binding: FragmentPostAdFinishBinding
    private lateinit var viewModel: AdViewModel
    private var errorSnackbar: Snackbar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_ad_finish,
            container,
            false)
        viewModel = ViewModelProviders.of(this).get(AdViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.postAd(
            Ad(
                "",
                PostAdFinishFragmentArgs.fromBundle(arguments!!).modelId,
                PostAdFinishFragmentArgs.fromBundle(arguments!!).versionId,
                "",
                PostAdFinishFragmentArgs.fromBundle(arguments!!).manufacturerId,
                "",
                PostAdFinishFragmentArgs.fromBundle(arguments!!).year,
                PostAdFinishFragmentArgs.fromBundle(arguments!!).distance,
                PostAdFinishFragmentArgs.fromBundle(arguments!!).description,
                "2",
            "Hanene",
            "BBA",
            "",
                PostAdFinishFragmentArgs.fromBundle(arguments!!).price,
            "",
            "",
            ""
        )
        )
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
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
       /* viewAdDetailAction.setOnClickListener {
           handleFinishClick(view)
        }*/

        finishPostAdAction.setOnClickListener{
            var action = PostAdFinishFragmentDirections.actionPostAdFinishToAdsFragment()
            view.setOnClickListener { v: View ->
                v.findNavController().navigate(action)
            }
        }
    }

    private fun handleFinishClick(view: View) {
        val action = PostAdFinishFragmentDirections
            .actionPostAdFinishToAdDetailsFragment(
                viewModel.newAd.value!!.id,
                viewModel.newAd.value!!.manufacturer,
                viewModel.newAd.value!!.manufacturer_name,
                viewModel.newAd.value!!.model,
                viewModel.newAd.value!!.version,
                viewModel.newAd.value!!.version_name,
                viewModel.newAd.value!!.year,
                viewModel.newAd.value!!.minPrice,
                viewModel.newAd.value!!.distance,
                viewModel.newAd.value!!.date,
                viewModel.newAd.value!!.description,
                "",
                "",
                "",
                viewModel.newAd.value!!.automobilist,
                viewModel.newAd.value!!.automobilist_username,
                viewModel.newAd.value!!.automobilist_address
            )
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }
    }




}
