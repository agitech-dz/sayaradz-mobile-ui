package com.example.sayaradz_mobile.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sayaradz.Data.NewCarList
import com.example.sayaradz_mobile.Data.NewCars
import com.example.sayaradz_mobile.Model.Car
import com.example.sayaradz_mobile.R

class CarAdapter(var itemList:List<NewCars>): RecyclerView.Adapter<CarAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.version_list_items, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.carVersion.text = itemList.get(p1).id
        p0.txtview1.text = "voiture"
        p0.txtView18.text = "Version"

        p0.carModel.text = itemList.get(p1).version
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val carVersion = itemView.findViewById<TextView>(R.id.versionlName)

        val carModel = itemView.findViewById<TextView>(R.id.ModelName)

        val txtview1 = itemView.findViewById<TextView>(R.id.textView4)
        val txtView18 = itemView.findViewById<TextView>(R.id.textView18)
    }


}
