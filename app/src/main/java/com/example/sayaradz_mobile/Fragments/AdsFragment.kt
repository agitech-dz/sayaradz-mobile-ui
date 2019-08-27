package com.example.sayaradz_mobile.Fragments

import android.os.Bundle
import android.view.*
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import com.example.sayaradz_mobile.Model.Ad
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.databinding.FragmentAdsBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.sayaradz_mobile.viewmodel.AdsListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_ads.*

class AdsFragment : Fragment() {

    companion object {
        val instance = AdsFragment()
    }

    private lateinit var binding: FragmentAdsBinding
    private lateinit var viewModel: AdsListViewModel
    private var errorSnackbar: Snackbar? = null

    private var adsList = ArrayList<Ad>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_ads, container, false)
                // val view = inflater.inflate(R.layout.fragment_ads, container, false)
        viewModel = ViewModelProviders.of(this).get(AdsListViewModel::class.java)
        viewModel.loadAds()
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.viewModel = viewModel

        //val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

      /*  val rvAdapter = UsedCarAdapter(adsList, context!!)
        recyclerView.adapter = rvAdapter*/
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE


        return binding.root
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction("Retry", viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAdAction.setOnClickListener {
            val action = AdsFragmentDirections.actionAdsFragmentToPostAdManufacturerFragment()
            it.findNavController().navigate(action)
        }
    }

    //RecycleView--------------------------------------------
  /*  private fun setUpAdsRecycleView(rootView: View, list: MutableList<Ad>) {
        var recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        //realtime list change
        var adapter = UsedCarAdapter(list, context!!)
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

    }*/



}
