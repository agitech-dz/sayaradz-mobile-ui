package com.example.sayaradz_mobile.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sayaradz_mobile.Fragments.HomeFragmentDirections
import com.example.sayaradz_mobile.Model.Version
import com.example.sayaradz_mobile.R

class VersionAdapter (var itemList:List<Version>,val context: Context ): RecyclerView.Adapter<VersionAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.version_item, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.model.text = itemList.get(p1).model
        p0.version.text = itemList.get(p1).name
        p0.price.text = itemList.get(p1).tarif_price.toString() + " DZD"
        Glide.with(context).load(itemList.get(p1).image).into(p0.image)
        handleClick(p0.container)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val container = itemView.findViewById<View>(R.id.itemContainer)
        val model = itemView.findViewById<TextView>(R.id.model)
        val version = itemView.findViewById<TextView>(R.id.version)
        val image = itemView.findViewById<ImageView>(R.id.image)
        val price = itemView.findViewById<TextView>(R.id.price)
    }

    private fun handleClick(view: View) {
        val action = HomeFragmentDirections.actionHomeFragmentToVersionFragment()
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }

    }



}