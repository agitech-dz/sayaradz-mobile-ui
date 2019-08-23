package com.example.sayaradz_mobile.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.sayaradz_mobile.Adapters.BrandAdapter
import com.example.sayaradz_mobile.Adapters.CarAdapter
import com.example.sayaradz_mobile.Adapters.UsedCarAdapter
import com.example.sayaradz_mobile.Model.Ad
import com.example.sayaradz_mobile.Model.Car
import com.example.sayaradz_mobile.R



class AdsFragment : Fragment() {

    companion object {
        val instance = AdsFragment()
    }

    private var adsList = ArrayList<Ad>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ads, container, false)
        adsList.add(Ad("Tesla", "Tesla", "Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val rvAdapter = UsedCarAdapter(adsList, context!!)
        recyclerView.adapter = rvAdapter

        // Inflate the layout for this fragment
        return view
    }
}
