package com.example.sayaradz_mobile.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.databinding.FragmentPostAdManufacturerBinding
import com.example.sayaradz_mobile.viewmodel.AdSpinnersViewModel
import com.google.android.material.snackbar.Snackbar
import com.tiper.MaterialSpinner
import kotlinx.android.synthetic.main.fragment_post_ad_manufacturer.*
import kotlinx.android.synthetic.main.fragment_post_ad_manufacturer.view.*

class PostAdManufacturer : Fragment() {

    private lateinit var binding: FragmentPostAdManufacturerBinding
    private lateinit var viewModel: AdSpinnersViewModel
    private var errorSnackbar: Snackbar? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_ad_manufacturer,
            container,
            false
        )
        viewModel = ViewModelProviders.of(this).get(AdSpinnersViewModel::class.java)
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
       /* var manufacturerList = ArrayList<String>()

        manufacturerList.add("Ferari")
        manufacturerList.add("BMW")
        manufacturerList.add("Audi")
        manufacturerList.add("Ferari1")
        manufacturerList.add("BMW1")
        manufacturerList.add("Audi1")
        manufacturerList.add("Ferari2")
        manufacturerList.add("BMW2")
        manufacturerList.add("Audi2")

        manufacturerSpinner.item = manufacturerList*/
       /* manufacturerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int) {
                Toast.makeText(context, position, Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }*/
    }
}
