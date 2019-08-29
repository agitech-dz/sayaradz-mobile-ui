package com.example.sayaradz_mobile.Fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sayaradz_mobile.Adapters.NotificationAdapter
import com.example.sayaradz_mobile.Model.Notification
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.databinding.FragmentInboxBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

class InboxFragment : Fragment() {

    companion object {
        val instance = InboxFragment()
    }

    private var notificationList = ArrayList<Notification>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentInboxBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_inbox, container, false)
        // val view = inflater.inflate(R.layout.fragment_ads, container, false)
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        notificationList.add(Notification(0,"Command", "Your Tesla Model 3 Car is Ready!"))
        setUpNotificationRecyclerView(binding.root, notificationList)



        return binding.root
    }

    //RecycleView--------------------------------------------
    private fun setUpNotificationRecyclerView(rootView: View, list: MutableList<Notification>) {
        var recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        //realtime list change
        var adapter = NotificationAdapter(list, context!!)
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
