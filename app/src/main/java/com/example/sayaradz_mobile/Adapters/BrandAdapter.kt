package com.example.sayaradz_mobile.Adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sayaradz_mobile.Model.Brand
import com.example.sayaradz_mobile.R

class BrandAdapter(var itemList:List<Brand>): RecyclerView.Adapter<BrandAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.brand_item, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.brandName.text = itemList.get(p1).brandName
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val brandName = itemView.findViewById<TextView>(R.id.carName)

    }


}
