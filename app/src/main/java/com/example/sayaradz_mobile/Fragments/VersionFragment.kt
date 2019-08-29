package com.example.sayaradz_mobile.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.sayaradz.Model.RestService
import com.example.sayaradz_mobile.Data.DataUtil
import com.example.sayaradz_mobile.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import com.example.sayaradz.Data.Version
import com.example.sayaradz_mobile.Adapters.VersionAdapters

class VersionFragment : Fragment() {


    companion object {
        val instance = VersionFragment()
    }

    private var VersionList = ArrayList<Version>()
    private val dataUtil = DataUtil()
    private var ChoosedVersion : Version? = null

    fun getVersion() : Version {return ChoosedVersion!!}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_version, container, false)
        val service = dataUtil.getRetrofit(getContext()!!).create(RestService::class.java)

        val idModel = ModelFragment.instance.getIdModelClicked()
        val call = service.ListVersion(idModel,1, 5)
        call.enqueue(object : Callback<MutableList<Version>> {
            override fun onFailure(call: Call<MutableList<Version>>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty " + t.message)
            }

            override fun onResponse(
                call: retrofit2.Call<MutableList<Version>>,
                response: Response<MutableList<Version>>
            ) {
                if (response.code() == 200) {

                    // Thread.sleep(40000)
                    var MarqueList = response.body()
                    Log.d("Tmarque ", "here you are you had that option ")
                    if (MarqueList != null) {

                        VersionList.addAll(MarqueList!!)
                        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewVersion)
                        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
                        val rvAdapter = VersionAdapters(VersionList)
                        recyclerView.adapter = rvAdapter
                        recyclerView.addOnItemTouchListener(
                            OnClickItem(
                                getContext()!!,
                                recyclerView,
                                object : OnItemClickListener {

                                    override fun onItemClick(view: View, position: Int) {
                                        VersionFragment.instance.ChoosedVersion = VersionList.get(position)
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
