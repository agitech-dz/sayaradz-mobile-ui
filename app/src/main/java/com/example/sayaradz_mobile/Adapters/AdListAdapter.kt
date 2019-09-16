package com.example.sayaradz_mobile.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sayaradz_mobile.Fragments.AdsFragmentDirections
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.data.Ad
import com.example.sayaradz_mobile.viewmodel.AdViewModel
import com.example.sayaradz_mobile.databinding.AdItemBinding
class AdListAdapter : RecyclerView.Adapter<AdListAdapter.ViewHolder>() {
    private lateinit var adsList: List<Ad>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdListAdapter.ViewHolder {
        val binding: AdItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.ad_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdListAdapter.ViewHolder, position: Int) {
        holder.bind(adsList[position])
        Log.e("ads list", adsList.toString())
        handleClick(holder.container, adsList[position])
       // Glide.with(context).load(adsList[position].photo1).into(holder.adImage)
    }

    override fun getItemCount(): Int {
        return if (::adsList.isInitialized) adsList.size else 0
    }

    fun updatePostList(adsList: List<Ad>) {
        this.adsList = adsList
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: AdItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = AdViewModel()
        val container: View = binding.itemContainer
        val adImage: ImageView = binding.adImage

        fun bind(ad: Ad) {
            if(ad.photo1!=null){

                Glide.with(container)
                    .load(ad.photo1)
                    .into(adImage)
            }
            viewModel.bind(ad)

            binding.viewModel = viewModel
        }


    }
    private fun handleClick(view: View, ad: Ad) {
        val action = AdsFragmentDirections
            .actionAdsFragmentToAdDetailsFragment(
                adId = ad.id,
                adManufacturer = ad.manufacturer,
                adManufacturerName = ad.manufacturer_name,
                adModel = ad.model,
                adVersion = ad.version,
                adVersionName = ad.version_name,
                adYear = ad.year,
                adMinPrice = ad.minPrice,
                adDistance = ad.distance,
                adDate = ad.date,
                adDescription = ad.description,
                adPhoto1 = if(ad.photo1!=null) ad.photo1 else "",
                adPhoto2 = if(ad.photo2!=null) ad.photo2 else "",
                adPhoto3 = if(ad.photo3!=null) ad.photo3 else "",
                adAutomobilist = ad.automobilist,
                adAutomobilistUserName = ad.automobilist_username,
                adAutomobilistAddress = ad.automobilist_address,
                adEnergy = ad.energy,
                adNbPersons = ad.personsNumber
            )
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }
    }
}

