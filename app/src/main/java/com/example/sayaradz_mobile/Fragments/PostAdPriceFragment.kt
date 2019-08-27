package com.example.sayaradz_mobile.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.sayaradz_mobile.R
import kotlinx.android.synthetic.main.fragment_ad_details.*
import kotlinx.android.synthetic.main.fragment_post_ad_description.*
import kotlinx.android.synthetic.main.fragment_post_ad_finish.*
import kotlinx.android.synthetic.main.fragment_post_ad_price.*


class PostAdPriceFragment : Fragment() {

    private lateinit var binding: com.example.sayaradz_mobile.databinding.FragmentPostAdPriceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_ad_price,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addPriceAction.setOnClickListener {
            val action = PostAdPriceFragmentDirections.actionPostAdPriceToAdFinishFragment(
                PostAdPriceFragmentArgs.fromBundle(arguments!!).manufacturerId,
                PostAdPriceFragmentArgs.fromBundle(arguments!!).modelId,
                PostAdPriceFragmentArgs.fromBundle(arguments!!).versionId,
                PostAdPriceFragmentArgs.fromBundle(arguments!!).year,
                PostAdPriceFragmentArgs.fromBundle(arguments!!).distance,
                PostAdPriceFragmentArgs.fromBundle(arguments!!).nbPersons,
                PostAdPriceFragmentArgs.fromBundle(arguments!!).energy,
                PostAdPriceFragmentArgs.fromBundle(arguments!!).description,
                adPrice.text.toString()
            )
            it.findNavController().navigate(action)
        }
    }



}
