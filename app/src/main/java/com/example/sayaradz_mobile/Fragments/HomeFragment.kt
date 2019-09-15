package com.example.sayaradz_mobile.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sayaradz_mobile.Adapters.ManufacturerAdapter
import com.example.sayaradz_mobile.Adapters.ModelAdapter
import com.example.sayaradz_mobile.Adapters.VersionAdapter
import com.example.sayaradz_mobile.HttpRequests.RestService
import com.example.sayaradz_mobile.HttpRequests.Retrofit
import com.example.sayaradz_mobile.Model.Car
import com.example.sayaradz_mobile.Model.Manufacturer
import com.example.sayaradz_mobile.Model.Model
import com.example.sayaradz_mobile.Model.Version
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.Utils.Utilities
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {



    private var manufacturerList = ArrayList<Manufacturer>()
    private var ModelList = ArrayList<Model>()
    private var VersionList = ArrayList<Version>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        if(!Utilities.hasNetwork(context!!)){
            progressBarVersion.visibility = View.GONE
            progressBarModels.visibility = View.GONE
            progressBarManufacturers.visibility = View.GONE
        }else{
            if(manufacturerList.count()>0){
                progressBarManufacturers.visibility = View.GONE

            }
            if(ModelList.count()>0){
                progressBarModels.visibility = View.GONE
            }
            if(VersionList.count()>0){
                progressBarVersion.visibility = View.GONE
            }
        }




        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val service = Retrofit.getRetrofit().create(RestService::class.java)


        var call = service.ListMarque(1, 5)
        call.enqueue(object : Callback<MutableList<Manufacturer>> {
            override fun onFailure(call: Call<MutableList<Manufacturer>>, t: Throwable) {
                progressBarManufacturers.visibility = View.GONE
                Log.d("fail ", "you've got it but with a big fail shitty " + t.message)
            }

            override fun onResponse(call: retrofit2.Call<MutableList<Manufacturer>>, response: Response<MutableList<Manufacturer>>) {
                progressBarManufacturers.visibility = View.GONE
                if (response.code() == 200) {
                    // Thread.sleep(40000)

                    var MarqueList = response.body()
                    if (MarqueList != null) {

                        manufacturerList.addAll(MarqueList)
                        val recyclerView4 = recyclerViewManufacturer
                        recyclerView4.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        val manufacturerAdapter = ManufacturerAdapter(manufacturerList,context!!)
                        recyclerView4.adapter = manufacturerAdapter

//                        recyclerView4.addOnItemTouchListener(
//                            BrandsFragment.OnClickItem(
//                                getContext()!!,
//                                recyclerView4,
//                                object : BrandsFragment.OnItemClickListener {
//
//                                    override fun onItemClick(view: View, position: Int) {
//                                        HomeFragment.instance.idBrandClicked = MarqueList.get(position).id
//                                        val newCase = ModelFragment.instance
//                                        val transaction = fragmentManager!!.beginTransaction()
//                                        transaction.replace(R.id.containFragment, newCase)
//                                            .addToBackStack("transaction_name")
//                                            .commit()
//                                    }
//
//                                    override fun onItemLongClick(view: View?, position: Int) {
//                                        TODO("do nothing")
//                                    }
//                                })
//                        )



                    }
                }
            }
        })
        val modelCall = service.ListModel(1, 5)
        modelCall.enqueue(object : Callback<MutableList<Model>> {
            override fun onFailure(call: Call<MutableList<Model>>, t: Throwable) {
                progressBarModels.visibility = View.GONE
                Log.d("fail ", "you've got it but with a big fail shitty " + t.message)
            }

            override fun onResponse(
                call: retrofit2.Call<MutableList<Model>>,
                response: Response<MutableList<Model>>
            ) {
                progressBarModels.visibility = View.GONE
                if (response.code() == 200) {


                    // Thread.sleep(40000)
                    var MarqueList = response.body()
                    Log.d("Tmarque ", "here you are you had that option ")
                    if (MarqueList != null) {

                        ModelList.addAll(MarqueList!!)
                        val recyclerView = recyclerViewModel
                        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        val rvAdapter = ModelAdapter(ModelList,context!!)
                        recyclerView.adapter = rvAdapter
//                        recyclerView.addOnItemTouchListener(
//                            OnClickItem(
//                                context!!,
//                                recyclerView,
//                                object : OnItemClickListener {
//
//                                    override fun onItemClick(view: View, position: Int) {
//                                        ModelFragment.instance.idModelClicked = ModelList.get(position).id
//                                        val newCase = VersionFragment.instance
//                                        val transaction = fragmentManager!!.beginTransaction()
//                                        transaction.replace(R.id.containerFragModel, newCase)
//                                            .addToBackStack("transaction_name")
//                                            .commit()
//                                    }
//
//                                    override fun onItemLongClick(view: View?, position: Int) {
//
//                                    }
//                                })
//                        )

                    }
                }
            }
        })
        val versionCall = service.ListVersion(1, 5)
        versionCall.enqueue(object : Callback<MutableList<Version>> {
            override fun onFailure(call: Call<MutableList<Version>>, t: Throwable) {
                progressBarVersion.visibility = View.GONE
                Log.d("fail ", "you've got it but with a big fail shitty " + t.message)
            }

            override fun onResponse(
                call: retrofit2.Call<MutableList<Version>>,
                response: Response<MutableList<Version>>
            ) {
                progressBarVersion.visibility = View.GONE
                if (response.code() == 200) {

                    // Thread.sleep(40000)
                    var MarqueList = response.body()
                    Log.d("Tmarque ", "here you are you had that option ")
                    if (MarqueList != null) {

                        VersionList.addAll(MarqueList!!)
                        val recyclerView = recyclerViewVersion
                        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        val rvAdapter = VersionAdapter(VersionList,context!!)
                        recyclerView.adapter = rvAdapter
//                        recyclerView.addOnItemTouchListener(
//                            OnClickItem(
//                                getContext()!!,
//                                recyclerView,
//                                object : OnItemClickListener {
//
//                                    override fun onItemClick(view: View, position: Int) {
//                                        VersionFragment.instance.ChoosedVersion = VersionList.get(position)
//                                        val newCase = FichTechFragment.instance
//                                        val transaction = fragmentManager!!.beginTransaction()
//                                        transaction.replace(R.id.containuerVersion, newCase)
//                                            .addToBackStack("transaction_name")
//                                            .commit()
//
//
//                                    }
//
//                                    override fun onItemLongClick(view: View?, position: Int) {
//                                        TODO("do nothing")
//                                    }
//                                })
//                        )

                    }
                }
            }
        })


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
