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
import com.example.sayaradz_mobile.Model.Model
import com.example.sayaradz_mobile.R

class ModelAdapter (var itemList:List<Model>, val context: Context ): RecyclerView.Adapter<ModelAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.model_item, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.model.text = itemList.get(p1).name
        p0.manufacturer.text = itemList.get(p1).manufacturer_name
        Glide.with(context).load(itemList.get(p1).image).into(p0.image)
        handleClick(p0.container)




    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val container = itemView.findViewById<View>(R.id.itemContainer)
        val model = itemView.findViewById<TextView>(R.id.model)
        val manufacturer = itemView.findViewById<TextView>(R.id.manufacturer)
        val image = itemView.findViewById<ImageView>(R.id.image)

    }

    private fun handleClick(view: View) {
        val action = HomeFragmentDirections.actionHomeFragmentToModelFragment()
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }

    }



}