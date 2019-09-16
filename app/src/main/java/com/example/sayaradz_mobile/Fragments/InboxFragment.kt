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
import com.example.sayaradz_mobile.Adapters.NotificationAdapter
import com.example.sayaradz_mobile.HttpRequests.RestService
import com.example.sayaradz_mobile.HttpRequests.Retrofit
import com.example.sayaradz_mobile.Model.Notification
import com.example.sayaradz_mobile.Model.NotificationBody
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.Utilities.Utilities
import com.example.sayaradz_mobile.databinding.FragmentInboxBinding

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class InboxFragment : Fragment() {


    private var notificationList = ArrayList<Notification>()
    private var compositeDisposable: CompositeDisposable? = null
    private var adapter: NotificationAdapter? = null
    private var recipient = 8
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentInboxBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_inbox, container, false)
        rootView = binding.root
        if(notificationList.count() > 0 || !Utilities.hasNetwork(context!!)){
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
        recipient = Utilities.retrieveAuthResponse(context!!).automobilist_id
        if (Utilities.hasNetwork(context!!)){
            compositeDisposable?.add(restService.getNotifications(recipient)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleNotificationsResponse,this::handleError))

        }else{
            Toast.makeText(context,"Connexion Internet Impossible",Toast.LENGTH_LONG).show()


        }



    }
    private fun handleError(t:Throwable){
        Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()

    }
    private fun handleNotificationsResponse(offerNotificationsList: List<NotificationBody>){

        val progressBar = rootView!!.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE
        this.notificationList.addAll(offerNotificationsList.map { o -> Notification(o) })
        setUpNotificationRecyclerView()



    }


    //RecycleView--------------------------------------------
    private fun setUpNotificationRecyclerView() {



        var recyclerView = rootView!!.findViewById<RecyclerView>(R.id.recyclerView)


        adapter = NotificationAdapter(notificationList, context!!)
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

    }



}
