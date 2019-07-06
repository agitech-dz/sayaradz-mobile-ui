package com.example.sayaradz_mobile.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sayaradz_mobile.Model.Car
import com.example.sayaradz_mobile.R

class CarAdapter(var itemList:List<Car>): RecyclerView.Adapter<CarAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.car_item, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.carName.text = itemList.get(p1).carName
        p0.carPrice.text = itemList.get(p1).carPrice
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val carName = itemView.findViewById<TextView>(R.id.brandName)

        val carPrice = itemView.findViewById<TextView>(R.id.carPrice)
    }


}
