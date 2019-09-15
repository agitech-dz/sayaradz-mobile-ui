package com.example.sayaradz_mobile.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sayaradz_mobile.Model.Model
import com.example.sayaradz_mobile.R

class ModelListAdapter (var itemList:List<Model>, val context: Context ): RecyclerView.Adapter<ModelListAdapter.ViewHolder>(){
    var itemChanged = MutableLiveData<Boolean>()

    init {
        itemChanged.value = false
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.model_list_item, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.model.text = itemList.get(p1).name
        p0.manufacturer.text = itemList.get(p1).manufacturer_name
        Glide.with(context).load(itemList.get(p1).image).into(p0.image)




    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val model = itemView.findViewById<TextView>(R.id.model)
        val manufacturer = itemView.findViewById<TextView>(R.id.manufacturer)
        val image = itemView.findViewById<ImageView>(R.id.image)

    }



}