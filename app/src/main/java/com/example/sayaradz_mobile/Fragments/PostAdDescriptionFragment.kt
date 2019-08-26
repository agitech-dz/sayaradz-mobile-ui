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
import kotlinx.android.synthetic.main.fragment_post_ad_manufacturer.*
import kotlinx.android.synthetic.main.fragment_post_ad_photos.*


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



}
