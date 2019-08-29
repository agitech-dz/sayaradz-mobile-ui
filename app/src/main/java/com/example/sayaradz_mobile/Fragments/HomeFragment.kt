package com.example.sayaradz_mobile.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import  com.example.sayaradz_mobile.Data.DataUtil
import android.widget.LinearLayout
import android.widget.TextView
import com.example.sayaradz.Data.Manufactors
import com.example.sayaradz.Model.RestService
import com.example.sayaradz_mobile.Adapters.BrandAdapter
import com.example.sayaradz_mobile.Adapters.CarAdapter
import com.example.sayaradz_mobile.Model.Car
import com.example.sayaradz_mobile.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response









class HomeFragment : Fragment() {

    companion object {
        val instance = HomeFragment()
    }
    private var idBrandClicked: Int = 0
    private var carList = ArrayList<Car>()
    private var brandList = ArrayList<String>()
    private val dataUtil = DataUtil()


    fun getIdBrandClicked() : Int {
        return idBrandClicked
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val AddAllBrands = view.findViewById<TextView>(R.id.seeAllBrands)

        AddAllBrands.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                val newCase = BrandsFragment.instance
                val transaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.containFragment, newCase)
                    .addToBackStack("transaction_name")
                    .commit()
            }
        })


        carList.add(Car("BMW 520d", "5.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))
        carList.add(Car("BMW 320d", "3.000.000", ""))

        Log.d("Start ", "Start the brandlist getting" )
        val service = dataUtil.getRetrofit(getContext()!!).create(RestService::class.java)

        var marks: ArrayList<String>? = arrayListOf()
        val call = service.ListMarque(1, 5)
        call.enqueue(object : Callback<MutableList<Manufactors>> {
            override fun onFailure(call: Call<MutableList<Manufactors>>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty " + t.message)
            }

            override fun onResponse(call: retrofit2.Call<MutableList<Manufactors>>, response: Response<MutableList<Manufactors>>) {
                if (response.code() == 200) {
                    // Thread.sleep(40000)
                    var MarqueList = response.body()
                    if (MarqueList != null) {
                        var j =0
                        for(i in MarqueList ){
                            marks!!.add( i.name)
                        }

                        brandList.addAll(marks!!)
                        val recyclerView4 = view.findViewById<RecyclerView>(R.id.recyclerView4)
                        recyclerView4.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
                        val brandAdapter = BrandAdapter(brandList)
                        recyclerView4.adapter = brandAdapter

                        recyclerView4.addOnItemTouchListener(
                            BrandsFragment.OnClickItem(
                                getContext()!!,
                                recyclerView4,
                                object : BrandsFragment.OnItemClickListener {

                                    override fun onItemClick(view: View, position: Int) {
                                        HomeFragment.instance.idBrandClicked = MarqueList.get(position).id
                                        val newCase = ModelFragment.instance
                                        val transaction = fragmentManager!!.beginTransaction()
                                        transaction.replace(R.id.containFragment, newCase)
                                            .addToBackStack("transaction_name")
                                            .commit()
                                    }

                                    override fun onItemLongClick(view: View?, position: Int) {
                                        TODO("do nothing")
                                    }
                                })
                        )



                    }
                }
            }
        })






        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerView2 = view.findViewById<RecyclerView>(R.id.recyclerView2)
        val recyclerView3 = view.findViewById<RecyclerView>(R.id.recyclerView3)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)

        val rvAdapter = CarAdapter(carList)

        recyclerView.adapter = rvAdapter
        recyclerView2.adapter = rvAdapter
        recyclerView3.adapter = rvAdapter



        return view
    }





    class OnClickItem(context: Context, recyclerView: RecyclerView, private val mListener: OnItemClickListener?) :
        RecyclerView.OnItemTouchListener {

        private val mGestureDetector: GestureDetector

        init {

            mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val childView = recyclerView.findChildViewUnder(e.x, e.y)

                    if (childView != null && mListener != null) {
                        mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
            val childView = view.findChildViewUnder(e.x, e.y)

            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
            }

            return false
        }

        override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)

        fun onItemLongClick(view: View?, position: Int)
    }




}
