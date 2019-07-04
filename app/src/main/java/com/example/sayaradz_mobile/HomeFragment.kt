package com.example.sayaradz_mobile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView


class HomeFragment : Fragment() {

    companion object {
        val instance = HomeFragment()
    }
    private var carList = ArrayList<Car>()
    private var brandList = ArrayList<Brand>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        carList.add(Car("BMW 520d","5.000.000da",""))
        carList.add(Car("BMW 320d","3.000.000da",""))
        carList.add(Car("BMW 320d","3.000.000da",""))
        carList.add(Car("BMW 320d","3.000.000da",""))
        carList.add(Car("BMW 320d","3.000.000da",""))
        carList.add(Car("BMW 320d","3.000.000da",""))
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
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerView4.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        val rvAdapter = CarAdapter(carList)
        val brandAdapter = BrandAdapter(brandList)
        recyclerView.adapter = rvAdapter
        recyclerView2.adapter = rvAdapter
        recyclerView3.adapter = rvAdapter
        recyclerView4.adapter = brandAdapter


        return view
    }

    class CarAdapter(var itemList:List<Car>): RecyclerView.Adapter<CarAdapter.ViewHolder>(){
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CarAdapter.ViewHolder {
            val v = LayoutInflater.from(p0?.context).inflate(R.layout.car_item, p0, false)

            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

        override fun onBindViewHolder(p0: CarAdapter.ViewHolder, p1: Int) {
            p0.carName.text = itemList.get(p1).carName
            p0.carPrice.text = itemList.get(p1).carPrice
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val carName = itemView.findViewById<TextView>(R.id.brandName)

            val carPrice = itemView.findViewById<TextView>(R.id.carPrice)
        }


    }

    class BrandAdapter(var itemList:List<Brand>): RecyclerView.Adapter<BrandAdapter.ViewHolder>(){
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BrandAdapter.ViewHolder {
            val v = LayoutInflater.from(p0?.context).inflate(R.layout.brand_item, p0, false)

            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

        override fun onBindViewHolder(p0: BrandAdapter.ViewHolder, p1: Int) {
            p0.brandName.text = itemList.get(p1).brandName
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val brandName = itemView.findViewById<TextView>(R.id.brandName)

        }


    }

}
