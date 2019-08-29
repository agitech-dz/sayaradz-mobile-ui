package com.example.sayaradz_mobile.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sayaradz_mobile.R


class FichTechFragment : Fragment() {


    companion object {
        val instance = FichTechFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fiche_technique, container, false)
        val version = VersionFragment.instance.getVersion()
        view.findViewById<TextView>(R.id.VersionName).text = version.name
        view.findViewById<TextView>(R.id.ModelName).text = version.model
        view.findViewById<TextView>(R.id.TarifPrice).text = version.tarif_price.toString()
        if(version.options!=null) view.findViewById<TextView>(R.id.option).text = version.options[0]

        return view
    }
}