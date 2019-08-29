package com.example.sayaradz_mobile.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sayaradz.Data.Models
import com.example.sayaradz_mobile.R

class ModelsAdapter (var itemList:List<Models> ): RecyclerView.Adapter<ModelsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.model_list_item, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.modelName.text = itemList.get(p1).name
        p0.ManifactorName.text = itemList.get(p1).manufacturer_name



    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val modelName = itemView.findViewById<TextView>(R.id.modelName)
        val ManifactorName = itemView.findViewById<TextView>(R.id.manifName)
    }



}
