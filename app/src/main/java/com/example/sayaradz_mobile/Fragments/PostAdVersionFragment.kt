package com.example.sayaradz_mobile.Fragments

import android.content.Context
import android.net.Uri
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
import com.example.sayaradz_mobile.databinding.FragmentPostAdVersionBinding
import com.example.sayaradz_mobile.viewmodel.AdSpinnersViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_post_ad_version.*


class PostAdVersionFragment : Fragment() {

    private lateinit var binding: FragmentPostAdVersionBinding
    private lateinit var viewModel: AdSpinnersViewModel
    private var errorSnackbar: Snackbar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_ad_version,
            container,
            false
        )
        viewModel = ViewModelProviders.of(this).get(AdSpinnersViewModel::class.java)
        viewModel.loadVersions(PostAdVersionFragmentArgs.fromBundle(arguments!!).modelId)
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
        selectVersionAction.setOnClickListener {

            val action = PostAdVersionFragmentDirections.actionPostAdVersionFragmentToPostAdExtraInformationsFragment()
            it.findNavController().navigate(action)

        }

    }

}
