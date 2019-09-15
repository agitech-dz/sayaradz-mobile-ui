package com.example.sayaradz_mobile.Fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sayaradz_mobile.Adapters.ModelListAdapter
import com.example.sayaradz_mobile.Adapters.VersionListAdapter
import com.example.sayaradz_mobile.HttpRequests.RestService
import com.example.sayaradz_mobile.HttpRequests.Retrofit
import com.example.sayaradz_mobile.Model.Version
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.Utils.Utilities
import com.example.sayaradz_mobile.databinding.FragmentModelBinding
import com.example.sayaradz_mobile.databinding.FragmentVersionBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class VersionFragment : Fragment() {


    private var versionList = ArrayList<Version>()
    private var compositeDisposable: CompositeDisposable? = null
    private var adapter: VersionListAdapter? = null
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentVersionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_version, container, false)
        rootView = binding.root
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE
        if(versionList.count() > 0 || !Utilities.hasNetwork(context!!)){
            val progressBar = rootView!!.findViewById<ProgressBar>(R.id.progressBar)
            progressBar.visibility = View.GONE
        }
        setUpNotificationRecyclerView()

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()

        loadData()


    }

    private fun loadData(){

        val restService = Retrofit.getRetrofit().create(RestService::class.java)

        if (Utilities.hasNetwork(context!!)){
            compositeDisposable?.add(restService.getVersions(1,50)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleVersionsResponse,this::handleError))

        }else{
            Toast.makeText(context,"Connexion Internet Impossible",Toast.LENGTH_LONG).show()


        }



    }
    private fun handleError(t:Throwable){
        Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()

    }
    private fun handleVersionsResponse(versionList: List<Version>){

        val progressBar = rootView!!.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE
        this.versionList.addAll(versionList)
        setUpNotificationRecyclerView()



    }


    //RecycleView--------------------------------------------
    private fun setUpNotificationRecyclerView() {



        var recyclerView = rootView!!.findViewById<RecyclerView>(R.id.recyclerView)


        adapter = VersionListAdapter(versionList, context!!)
        adapter!!.itemChanged.observe(this, Observer { state ->
            if (state) {
                adapter!!.notifyDataSetChanged()
                adapter!!.itemChanged.value = false
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

    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable?.clear()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE

    }



}
