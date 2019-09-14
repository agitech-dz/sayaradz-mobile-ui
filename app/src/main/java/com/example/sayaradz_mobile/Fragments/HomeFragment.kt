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
import com.example.sayaradz.Data.NewCarList
import com.example.sayaradz.Data.Version
import com.example.sayaradz.Model.RestService
import com.example.sayaradz_mobile.Adapters.BrandAdapter
import com.example.sayaradz_mobile.Adapters.CarAdapter
import com.example.sayaradz_mobile.Adapters.VersionAdapters
import com.example.sayaradz_mobile.Data.NewCars
import com.example.sayaradz_mobile.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response









class HomeFragment : Fragment() {

    companion object {
        val instance = HomeFragment()
    }
    private var VersionList = ArrayList<Version>()
    private var ChoosedVersion : Version? = null
    private var idBrandClicked: Int = 0
    private var carList = ArrayList<NewCars>()
    private var brandList = ArrayList<String>()
    private val dataUtil = DataUtil()


    fun getIdBrandClicked() : Int {
        return idBrandClicked
    }

    fun getVersion (): Version? {
        return ChoosedVersion
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

        val service = dataUtil.getRetrofit(getContext()!!).create(RestService::class.java)
          //get brands from the api !
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


        //get New cars :
        val idModel = "m1"
        val appel = service.ListVersion(idModel,1, 5)
        appel.enqueue(object : Callback<MutableList<Version>> {
            override fun onFailure(call: Call<MutableList<Version>>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty " + t.message)
            }

            override fun onResponse(
                call: retrofit2.Call<MutableList<Version>>,
                response: Response<MutableList<Version>>
            ) {

                if (response.code() == 200) {
                    var MarqueList = response.body()
                    if (MarqueList != null) {
                        Log.d("okiiiiiiiiiiiii ", "it's workiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiing " )

                        VersionList.addAll(MarqueList!!)
                        val recyclerView2 = view.findViewById<RecyclerView>(R.id.recyclerView2)
                        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
                        val rvAdapter = VersionAdapters(VersionList)
                        recyclerView2.adapter = rvAdapter

                        recyclerView2.addOnItemTouchListener(
                            VersionFragment.OnClickItem(
                                getContext()!!,
                                recyclerView2,
                                object : VersionFragment.OnItemClickListener {

                                    override fun onItemClick(view: View, position: Int) {
                                        HomeFragment.instance.ChoosedVersion = VersionList.get(position)
                                        val newCase = FichTechFragment.instance
                                        val transaction = fragmentManager!!.beginTransaction()
                                        transaction.replace(R.id.containuerVersion, newCase)
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




        //get newcars :


        val use = service.ListNewCars(1, 5)
        use.enqueue(object : Callback<NewCarList> {
            override fun onFailure(call: Call<NewCarList>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty " + t.message)
            }

            override fun onResponse(
                call: retrofit2.Call<NewCarList>,
                response: Response<NewCarList>
            ) {
                if (response.code() == 200) {

                    // Thread.sleep(40000)
                    var MarqueList = response.body()
                    Log.d("Tmarque ", "here you are you had that option ")
                    if (MarqueList != null) {

                        carList.addAll(MarqueList.getNewCars()!!)
                        val recyclerView3 = view.findViewById<RecyclerView>(R.id.recyclerView3)
                        recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
                        val rvAdapter = CarAdapter(carList)
                        recyclerView3.adapter = rvAdapter



                    }
                }
            }
        })


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
