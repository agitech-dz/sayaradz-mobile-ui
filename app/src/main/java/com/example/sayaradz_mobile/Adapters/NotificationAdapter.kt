package com.example.sayaradz_mobile.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sayaradz_mobile.Fragments.InboxFragment
import com.example.sayaradz_mobile.Fragments.InboxFragmentDirections
import com.example.sayaradz_mobile.Model.CommandNotification
import com.example.sayaradz_mobile.Model.Notification
import com.example.sayaradz_mobile.Model.OfferNotification
import com.example.sayaradz_mobile.R

class NotificationAdapter(var itemList:List<Notification>, val context: Context): RecyclerView.Adapter<NotificationAdapter.ViewHolder>(){

    var itemChanged = MutableLiveData<Boolean>()

    init {
        itemChanged.value = false
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.inbox_item, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.notificationTitle.text = itemList.get(p1).title
        p0.notificationDescription.text = itemList.get(p1).description
        Glide.with(context).load(itemList.get(p1).photo).into(p0.notificationImage)
        handleClick(p0.container, itemList[p1])

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView.findViewById<View>(R.id.itemContainer)
        val notificationTitle = itemView.findViewById<TextView>(R.id.notificationTitle)
        val notificationDescription = itemView.findViewById<TextView>(R.id.notificationDescription)
        val notificationImage = itemView.findViewById<ImageView>(R.id.notificationImage)
    }

    private fun handleClick(view: View, notification: Notification) {
        var action: NavDirections? = null
        when(notification){
            is CommandNotification -> {
                action = InboxFragmentDirections.actionInboxFragmentToCommandNotificationDetailsFragment(notification)

            }
            is OfferNotification -> {
                action = InboxFragmentDirections.actionInboxFragmentToOfferNotificationDetailsFragment(notification)

            }
        }

        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action!!)
        }

    }

}
