package com.example.sayaradz_mobile.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.sayaradz_mobile.Adapters.BrandAdapter
import com.example.sayaradz_mobile.Adapters.CarAdapter
import com.example.sayaradz_mobile.Model.Brand
import com.example.sayaradz_mobile.Model.Car
import com.example.sayaradz_mobile.R


class HomeFragment : Fragment() {


    private var carList = ArrayList<Car>()
    private var brandList = ArrayList<Brand>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        carList.add(Car("BMW 520d", "5.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        brandList.add(Brand("BMW"))
        brandList.add(Brand("Mercedes"))
        brandList.add(Brand("Audi"))
        brandList.add(Brand("Bently"))
        brandList.add(Brand("Ferari"))
        brandList.add(Brand("Lamborghini"))
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerView2 = view.findViewById<RecyclerView>(R.id.recyclerView2)
        val recyclerView3 = view.findViewById<RecyclerView>(R.id.recyclerView3)
        val recyclerView4 = view.findViewById<RecyclerView>(R.id.recyclerView4)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerView2.layoutManager =
            LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerView3.layoutManager =
            LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerView4.layoutManager =
            LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        val rvAdapter = CarAdapter(carList)
        val brandAdapter = BrandAdapter(brandList)
        recyclerView.adapter = rvAdapter
        recyclerView2.adapter = rvAdapter
        recyclerView3.adapter = rvAdapter
        recyclerView4.adapter = brandAdapter


        return view
    }



}
