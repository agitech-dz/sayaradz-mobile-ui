package com.example.sayaradz_mobile.Adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.sayaradz_mobile.Fragments.AdsFragmentDirections
import com.example.sayaradz_mobile.Model.Ad
import com.example.sayaradz_mobile.R

class UsedCarAdapter(var itemList:List<Ad>, val context: Context): RecyclerView.Adapter<UsedCarAdapter.ViewHolder>(){

    var itemChanged = MutableLiveData<Boolean>()

    init {
        itemChanged.value = false
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.ad_item, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.adBrand.text = itemList.get(p1).brand
        p0.adModelAndVersion.text = itemList.get(p1).model + " " + itemList.get(p1).version
        p0.adLocation.text = itemList[p1].wilaya
        p0.adMinPrice.text = itemList[p1].minPrice
        p0.adYearAndDistance.text = itemList[p1].year + " | " + itemList[p1].distance + "km"
        Glide.with(context).load(itemList.get(p1).photo1).into(p0.adImage)
        handleClick(p0.container, itemList[p1].id)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView.findViewById<View>(R.id.itemContainer)
        val adBrand = itemView.findViewById<TextView>(R.id.notificationTitle)
        val adModelAndVersion = itemView.findViewById<TextView>(R.id.notificationDescription)
        val adYearAndDistance = itemView.findViewById<TextView>(R.id.adYearAndDistance)
        val adMinPrice = itemView.findViewById<TextView>(R.id.adMinPrice)
        val adImage = itemView.findViewById<ImageView>(R.id.adImage)
        val adLocation = itemView.findViewById<TextView>(R.id.adLocation)
    }

    private fun handleClick(view: View, adId: String) {
        val action = AdsFragmentDirections.actionAdsFragmentToAdDetailsFragment(adId)
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }

    }

}
