package com.example.sayaradz_mobile.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sayaradz.Data.Version
import com.example.sayaradz_mobile.R

class VersionAdapters (var itemList:List<Version> ): RecyclerView.Adapter<VersionAdapters.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.version_list_items, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.versionName.text = itemList.get(p1).name
        p0.modelName.text = itemList.get(p1).model



    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val versionName = itemView.findViewById<TextView>(R.id.versionlName)
        val modelName = itemView.findViewById<TextView>(R.id.ModelName)
    }



}