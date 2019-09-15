package com.example.sayaradz_mobile.Adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val model = itemView.findViewById<TextView>(R.id.model)
        val year = itemView.findViewById<TextView>(R.id.yearAndDistance)
        val image = itemView.findViewById<ImageView>(R.id.image)
        val price = itemView.findViewById<TextView>(R.id.price)
    }


}
