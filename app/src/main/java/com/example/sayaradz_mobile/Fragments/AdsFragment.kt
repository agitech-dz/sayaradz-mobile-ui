package com.example.sayaradz_mobile.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.sayaradz_mobile.Adapters.BrandAdapter
import com.example.sayaradz_mobile.Adapters.CarAdapter
import com.example.sayaradz_mobile.Adapters.UsedCarAdapter
import com.example.sayaradz_mobile.Model.Car
import com.example.sayaradz_mobile.R



class AdsFragment : Fragment() {

    companion object {
        val instance = AdsFragment()
    }

    private var carList = ArrayList<Car>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ads, container, false)
        carList.add(Car("BMW 520d", "5.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val rvAdapter = UsedCarAdapter(carList)
        recyclerView.adapter = rvAdapter

        // Inflate the layout for this fragment
        return view
    }
}
