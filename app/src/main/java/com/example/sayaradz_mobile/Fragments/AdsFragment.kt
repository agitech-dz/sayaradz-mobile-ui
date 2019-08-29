package com.example.sayaradz_mobile.Fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sayaradz_mobile.Adapters.UsedCarAdapter
import com.example.sayaradz_mobile.Model.Ad
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.databinding.FragmentAdsBinding
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdsFragment : Fragment() {

    companion object {
        val instance = AdsFragment()
    }

    private var adsList = ArrayList<Ad>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAdsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_ads, container, false)
                // val view = inflater.inflate(R.layout.fragment_ads, container, false)
        adsList.add(Ad("Tesla", "Tesla", "Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        adsList.add(Ad("Tesla", "Tesla","Model 3", "Long Range", "description", "Diesel", "2018", "Alger", "Automatique", "12-12-2019 12:58", "1", "Hanene", "BBA", "5", "120000", "123000 $", "https://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Mountain-Pearl-620x349.png", "https://evcompare.io/upload/resize_cache/iblock/308/1200_800_2/308dc4582c277708cab15468fc5d6c36.jpg", "https://cleantechnica.com/files/2018/10/tesla-model-3-and-sister-in-law.png"))
        setUpAdsRecycleView(binding.root, adsList)

        /*val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val rvAdapter = UsedCarAdapter(adsList, context!!)
        recyclerView.adapter = rvAdapter*/

        return binding.root
    }

    //RecycleView--------------------------------------------
    private fun setUpAdsRecycleView(rootView: View, list: MutableList<Ad>) {
        var recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        //realtime list change
        var adapter = UsedCarAdapter(list, context!!)
        adapter.itemChanged.observe(this, Observer { state ->
            if (state) {
                adapter.notifyDataSetChanged()
                adapter.itemChanged.value = false
            }
        })
        recyclerView.adapter = adapter

        //setup the recucleview depending on screen orientation
        val screenOrientation =
            (context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.orientation
        when (screenOrientation) {
            Surface.ROTATION_0 -> recyclerView.layoutManager = LinearLayoutManager(context)
            else -> recyclerView.layoutManager = GridLayoutManager(context, 2)
        }
        recyclerView.setHasFixedSize(true)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE
    }



}
