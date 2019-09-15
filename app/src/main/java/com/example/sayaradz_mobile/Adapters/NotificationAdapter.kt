package com.example.sayaradz_mobile.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sayaradz_mobile.Fragments.InboxFragmentDirections
import com.example.sayaradz_mobile.HttpRequests.RestService
import com.example.sayaradz_mobile.HttpRequests.Retrofit
import com.example.sayaradz_mobile.Model.Notification
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.Utils.Utilities
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotificationAdapter(var itemList:List<Notification>, val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var itemChanged = MutableLiveData<Boolean>()

    init {
        itemChanged.value = false
    }

    override fun getItemViewType(position: Int): Int {
        if (itemList.get(position).unread) return 1
        return 0
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        when(p1){
            0 -> {
                val v = LayoutInflater.from(p0?.context).inflate(R.layout.inbox_item, p0, false)
                return ViewHolder0(v)

            }
            else ->{

                val v = LayoutInflater.from(p0?.context).inflate(R.layout.unread_inbox_item, p0, false)
                return ViewHolder1(v)

            }
        }



    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        when(p0.itemViewType){
            0 ->{
                val viewHolder0 = p0 as ViewHolder0
                viewHolder0.notificationTitle.text = itemList.get(p1).title
                viewHolder0.notificationDescription.text = itemList.get(p1).description
                viewHolder0.notificationDate.text = itemList.get(p1).date
                Glide.with(context).load(itemList.get(p1).photo).into(viewHolder0.notificationImage)
                handleClick(viewHolder0.container, itemList[p1])

            }
            1 -> {

                val viewHolder1 = p0 as ViewHolder1
                viewHolder1.notificationTitle.text = itemList.get(p1).title
                viewHolder1.notificationDescription.text = itemList.get(p1).description
                viewHolder1.notificationDate.text = itemList.get(p1).date
                Glide.with(context).load(itemList.get(p1).photo).into(viewHolder1.notificationImage)
                handleClick(viewHolder1.container, itemList[p1])

            }
        }



    }

    class ViewHolder0(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView.findViewById<View>(R.id.itemContainer)
        val notificationTitle = itemView.findViewById<TextView>(R.id.notificationTitle)
        val notificationDescription = itemView.findViewById<TextView>(R.id.notificationDescription)
        val notificationImage = itemView.findViewById<ImageView>(R.id.notificationImage)
        val notificationDate = itemView.findViewById<TextView>(R.id.notificationDate)
    }

    class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView.findViewById<View>(R.id.itemContainer)
        val notificationTitle = itemView.findViewById<TextView>(R.id.notificationTitle)
        val notificationDescription = itemView.findViewById<TextView>(R.id.notificationDescription)
        val notificationImage = itemView.findViewById<ImageView>(R.id.notificationImage)
        val notificationDate = itemView.findViewById<TextView>(R.id.notificationDate)
    }

    private fun handleClick(view: View, notification: Notification) {

        var action: NavDirections? = null
        when(notification.body!!.notification_type){
             "CV" -> {
                action = InboxFragmentDirections.actionInboxFragmentToCommandValidationNotificationFragment(notification)

            }
             "MC" -> {
                action = InboxFragmentDirections.actionInboxFragmentToFollowedModelChangeNotificationFragment(notification)

            }
            "VC"->{
                action = InboxFragmentDirections.actionInboxFragmentToFollowedVersionChangeNotificationFragment(notification)

            }
            "OA"->{
                action = InboxFragmentDirections.actionInboxFragmentToAcceptOfferNotificationFragment(notification)

            }
            "PO" -> action = InboxFragmentDirections.actionInboxFragmentToPostOfferNotificationFragment(notification)
        }


        view.setOnClickListener { v: View ->
            notification.unread = false
            notification.body!!.unread = false
            val compositeDisposable = CompositeDisposable()
            val restService = Retrofit.getRetrofit().create(RestService::class.java)
            compositeDisposable.add(restService.setNotificationAsRead(notification.body!!.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError))


            v.findNavController().navigate(action!!)
        }

    }
    private fun handleResponse(void: Void){

    }
    private fun handleError(t:Throwable){
        Log.e("ERROR",t.message)
    }

}
