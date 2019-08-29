package com.example.sayaradz_mobile.Adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sayaradz.Data.Manufactors
import com.example.sayaradz_mobile.R




class ListBrandAdapter (var itemList:List<Manufactors> ): RecyclerView.Adapter<ListBrandAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.brand_list_items, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.brandName.text = itemList.get(p1).name
        p0.brandNationality.text = itemList.get(p1).nationality



    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val brandName = itemView.findViewById<TextView>(R.id.brandName)
        val brandNationality = itemView.findViewById<TextView>(R.id.brandNation)
    }



}
