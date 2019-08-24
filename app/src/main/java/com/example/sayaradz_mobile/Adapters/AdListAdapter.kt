package com.example.sayaradz_mobile.Adapters

import android.content.Context
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
        handleClick(holder.container, adsList[position].id)
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

            viewModel.bind(ad)
            binding.viewModel = viewModel
        }


    }
    private fun handleClick(view: View, adId: String) {
        val action = AdsFragmentDirections.actionAdsFragmentToAdDetailsFragment(adId)
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }

    }
}

