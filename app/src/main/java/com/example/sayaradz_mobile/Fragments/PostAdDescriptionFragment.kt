package com.example.sayaradz_mobile.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.data.Manufacturer
import com.example.sayaradz_mobile.databinding.FragmentPostAdDescriptionBinding
import kotlinx.android.synthetic.main.fragment_post_ad_description.*


class PostAdDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentPostAdDescriptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            com.example.sayaradz_mobile.R.layout.fragment_post_ad_description,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDescriptionAction.setOnClickListener {
            val action = PostAdDescriptionFragmentDirections.actionPostAdDescritpionToPostAdPriceFragment(
                PostAdDescriptionFragmentArgs.fromBundle(arguments!!).manufacturerId,
                PostAdDescriptionFragmentArgs.fromBundle(arguments!!).modelId,
                PostAdDescriptionFragmentArgs.fromBundle(arguments!!).versionId,
                PostAdDescriptionFragmentArgs.fromBundle(arguments!!).year,
                PostAdDescriptionFragmentArgs.fromBundle(arguments!!).distance,
                PostAdDescriptionFragmentArgs.fromBundle(arguments!!).nbPersons,
                PostAdDescriptionFragmentArgs.fromBundle(arguments!!).energy,
                adDescription.text.toString()
            )
            it.findNavController().navigate(action)
        }
    }




}
