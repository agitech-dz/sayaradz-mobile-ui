package com.example.sayaradz_mobile.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.sayaradz_mobile.data.Version
import com.example.sayaradz_mobile.databinding.FragmentPostAdExtraInformationsBinding
import kotlinx.android.synthetic.main.fragment_post_ad_extra_informations.*
import kotlinx.android.synthetic.main.fragment_post_ad_version.*
import java.util.*


class PostAdExtraInformationsFragment : Fragment() {
    private lateinit var binding: FragmentPostAdExtraInformationsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            com.example.sayaradz_mobile.R.layout.fragment_post_ad_extra_informations,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //Years Spinner
        val years = ArrayList<String>()
        val thisYear = Calendar.getInstance().get(Calendar.YEAR)
        for (i in 1980..thisYear) {
            years.add(Integer.toString(i))
        }
        val yearsArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, years)
        yearsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearsSpinner.adapter = yearsArrayAdapter

        val energyTypes = ArrayList<String>()
        energyTypes.add("Electrique")
        energyTypes.add("Diesel")
        energyTypes.add("Hybride")
        energyTypes.add("GPL")
        energyTypes.add("Gazole")
        energyTypes.add("Essence")
        energyTypes.add("Fioul")
        val energyArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, energyTypes)
        energyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        energySpinner.adapter = energyArrayAdapter

        //nbPersons Spinner
        val nbPersons = ArrayList<String>()
        for (i in 1..12) {
            nbPersons.add(Integer.toString(i))
        }
        val nbpersonsArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, nbPersons)
        nbpersonsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        nbPersonsSpinner.adapter = nbpersonsArrayAdapter

        addExtraInfosAction.setOnClickListener {

            val action = PostAdExtraInformationsFragmentDirections.actionPostAdExtraInfosFragmentToPostAdPhotosFragment(
                PostAdExtraInformationsFragmentArgs.fromBundle(arguments!!).manufacturerId,
                PostAdExtraInformationsFragmentArgs.fromBundle(arguments!!).modelId,
                PostAdExtraInformationsFragmentArgs.fromBundle(arguments!!).versionId,
                yearsSpinner.selectedItem as String,
                adDistance.text.toString(),
                nbPersonsSpinner.selectedItem as String,
                energySpinner.selectedItem as String
            )

            it.findNavController().navigate(action)

        }

    }






}
