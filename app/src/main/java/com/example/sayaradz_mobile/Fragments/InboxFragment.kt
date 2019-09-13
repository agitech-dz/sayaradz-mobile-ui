package com.example.sayaradz_mobile.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sayaradz_mobile.Adapters.NotificationAdapter
import com.example.sayaradz_mobile.HttpRequests.RestService
import com.example.sayaradz_mobile.HttpRequests.Retrofit
import com.example.sayaradz_mobile.Model.CommandNotification
import com.example.sayaradz_mobile.Model.Notification
import com.example.sayaradz_mobile.Model.OfferNotification
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.databinding.FragmentInboxBinding

import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class InboxFragment : Fragment() {

    companion object {
        val instance = InboxFragment()
    }

    private var notificationList = ArrayList<Notification>()
    private var compositeDisposable: CompositeDisposable? = null
    private var adapter: NotificationAdapter? = null
    private val recipient = 3
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentInboxBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_inbox, container, false)
        compositeDisposable = CompositeDisposable()
//        notificationList.clear()
//        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!","CV","https://www.sciencesetavenir.fr/assets/img/2018/09/06/cover-r4x3w1000-5b91612a81afc-section-hero-background.jpg"))
//        notificationList.add(Notification(0,"Offer", "Your Tesla Model 3 Car is Ready!","OA","https://www.teslarati.com/wp-content/uploads/2019/06/tesla-model-s-refresh-concept.jpg"))
//        notificationList.add(Notification(0,"Offer", "Your Tesla Model 3 Car is Ready!","OA","https://st.motortrend.com/uploads/sites/10/2015/11/2015-tesla-model-s-sedan-angular-front.png"))
//        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!","CV","https://img.autoplus.fr/news/2019/04/24/1537947/1200%7C800%7C1af8c7e95f9df8de31cbc146.jpg"))
//        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!","CV","https://cdn.motor1.com/images/mgl/OjmPo/s1/tesla-model-s-render.jpg"))
//        notificationList.add(Notification(0,"Offer", "Your Tesla Model 3 Car is Ready!","OA","https://img.autoplus.fr/picture/tesla-motors/model-s/1503606/Tesla_Motors_Model_S_2016_44c4a-1200-800.jpg"))
//        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!","CV","https://www.forbes.fr/wp-content/uploads/2019/09/tesla-sans-cuir-740x370.jpg"))
//        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!","CV","https://www.protegez-vous.ca/var/protegez_vous/storage/images/_aliases/width_900px/medias/galerie-photo/tesla-model-3/1936/2715617-1-fre-CA/1936.jpg"))
//        notificationList.add(Notification(0,"Offer", "Your Tesla Model 3 Car is Ready!","OA","https://images.sudouest.fr/2017/04/17/58f4872c66a4bdfc5a1d122c/widescreen/1000x500/la-derniere-version-de-la-tesla-propose-une-face-avant-plus-elegante.jpg"))
//        notificationList.add(Notification(0,"Offer", "Your Tesla Model 3 Car is Ready!","OA","https://www.largus.fr/images/images/tesla-model-s_1.jpg?width=612&quality=80"))
//        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!","CV","https://cdn.shopify.com/s/files/1/0043/8471/8938/products/155674392269619844_812x.jpg?v=1556743983"))
//        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!","CV","https://www.challenges.fr/assets/img/2017/08/07/cover-r4x3w1000-5b1aa348121e9-tesla-model-3-73-jpeg.jpg"))
//        notificationList.add(Notification(0,"Offer", "Your Tesla Model 3 Car is Ready!","OA","https://the-drive.imgix.net/http%3A%2F%2Fd254andzyoxz3f.cloudfront.net%2Fcrictics-notebook-tesla-s-p90d-ludicrous-hero.jpg?w=1440&auto=compress%2Cformat&ixlib=js-1.4.1&s=08e88bf9c1aa3e82706feaa8ad106e52"))
//
        rootView = binding.root
        loadData()




        return rootView
    }

    private fun loadData(){

        val restService = Retrofit.getRetrofit().create(RestService::class.java)
        compositeDisposable?.add(restService.getCommandNotifications(recipient)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleCommandNotificationResponse))
        compositeDisposable?.add(restService.getOfferNotifications(recipient)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleOfferNotificationResponse))


    }
    private fun handleOfferNotificationResponse(offerNotificationsList: List<OfferNotification>){

        this.notificationList.addAll(offerNotificationsList.map { o -> Notification(o) })
        if (adapter == null) setUpNotificationRecyclerView()



    }

    private fun handleCommandNotificationResponse(commandNotificationList: List<CommandNotification>){

        this.notificationList.addAll(commandNotificationList.map { c -> Notification(c) })
        if (adapter == null) setUpNotificationRecyclerView()



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
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable?.clear()

    }



}
