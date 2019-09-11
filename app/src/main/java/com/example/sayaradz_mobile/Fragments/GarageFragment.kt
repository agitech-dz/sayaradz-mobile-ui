package com.example.sayaradz_mobile.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sayaradz_mobile.Activities.PaymentActivity
import com.example.sayaradz_mobile.Model.*
import com.example.sayaradz_mobile.R


class GarageFragment : Fragment() {

    companion object {
        val instance = GarageFragment()
    }

    lateinit var model : CurrentData // ViewModel to hold the current choices made by the user
    val selectedOptions : ArrayList<String?> = ArrayList() // Add Selected Options List
    val supply = Supply() // The supply that contains the vehicles

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_garage, container, false)

        val manager = DataManager.instance
        /** Generate some data **/
        manager.init()
        supply.fillSupply()

        model = ViewModelProviders.of(this).get(CurrentData::class.java)
        model.setVersionPrice(0)
        model.setColorPrice(0)
        model.setOptionsPrice(0)

        /***** Implement Observers *****/
        val brandObserver = Observer<CarBrand?> {newBrand ->
            var models = arrayOf<String?>()
            if (newBrand != null) {
                models = newBrand.modelsList()
            }
            adaptSpinner(view, view.findViewById(R.id.model), models, "Model")
        }

        val modelObserver = Observer<Model?> {newModel ->
            var versions = arrayOf<String?>()
            if (newModel != null) {
                versions = newModel.versionsList()
            }
            adaptSpinner(view, view.findViewById(R.id.versionTxt), versions, "Version")
        }

        val versionObserver = Observer<Version?> {newVersion ->
            var colors = arrayOf<String?>()
            if (newVersion != null) {
                colors = manager.colorsList()
                fillOptions(view.findViewById(R.id.options), newVersion.optionsList())
                model.setVersionPrice(newVersion.price)
            }
            adaptSpinner(view, view.findViewById(R.id.color), colors, "Color")
        }

        val colorObserver = Observer<Color?> {newColor ->
            if (newColor != null) {
                model.setColorPrice(newColor.price)
            }
        }

        val priceObserver = Observer<Long?> {newPrice ->
            if (newPrice != null) {
                setPrice(view, newPrice)
            }
        }

        val vcoPriceObserver = Observer<Long?> {
            model.updatePrice()
        }

        model.currentBrand.observe(this, brandObserver)
        model.currentModel.observe(this, modelObserver)
        model.currentVersion.observe(this, versionObserver)
        model.currentColor.observe(this, colorObserver)
        model.price.observe(this, priceObserver)

        model.versionPrice.observe(this, vcoPriceObserver)
        model.colorPrice.observe(this, vcoPriceObserver)
        model.optionsPrice.observe(this, vcoPriceObserver)

        val marques = manager.brandsList()
        adaptSpinner(view, view.findViewById(R.id.marque), marques, "Brand")

        view.findViewById<ImageButton>(R.id.checkButton).setOnClickListener {
            if (model.currentBrand.value == null) {
                displayMessage("Please select a brand first")
            }
            else if (model.currentModel.value == null) {
                displayMessage("Please select a model first")
            }
            else if (model.currentVersion.value == null) {
                displayMessage("Please select a version first")
            }
            else if (model.currentColor.value == null) {
                displayMessage("Please select a color first")
            }
            else {
                // Search the vehicle
                val res = supply.findVehicle(model.currentBrand.value?.name,
                                             model.currentModel.value?.name,
                                             model.currentVersion.value?.name,
                                             model.currentColor.value?.name,
                                             selectedOptions)
                if (res) {
                    // Popup the proper message when found
                    //displayMessage("The vehicle exists in the supply")
                    view.findViewById<ConstraintLayout>(R.id.cover).visibility = ConstraintLayout.VISIBLE
                    view.findViewById<ConstraintLayout>(R.id.popupSuccess).visibility = ConstraintLayout.VISIBLE
                    view.findViewById<ConstraintLayout>(R.id.contentLayout).visibility = ConstraintLayout.INVISIBLE
                }
                else {
                    // Popup the proper message when not found
                    //displayMessage("The vehicle does not exist in the supply")
                    view.findViewById<ConstraintLayout>(R.id.cover).visibility = ConstraintLayout.VISIBLE
                    view.findViewById<ConstraintLayout>(R.id.popupFail).visibility = ConstraintLayout.VISIBLE
                    view.findViewById<ConstraintLayout>(R.id.contentLayout).visibility = ConstraintLayout.INVISIBLE
                }
            }
        }

        view.findViewById<ImageView>(R.id.close).setOnClickListener {
            view.findViewById<ConstraintLayout>(R.id.popupFail).visibility = ConstraintLayout.GONE
            view.findViewById<ConstraintLayout>(R.id.cover).visibility = ConstraintLayout.INVISIBLE
            view.findViewById<ConstraintLayout>(R.id.contentLayout).visibility = ConstraintLayout.VISIBLE
        }
        view.findViewById<ImageView>(R.id.close2).setOnClickListener {
            view.findViewById<ConstraintLayout>(R.id.popupSuccess).visibility = ConstraintLayout.GONE
            view.findViewById<ConstraintLayout>(R.id.cover).visibility = ConstraintLayout.INVISIBLE
            view.findViewById<ConstraintLayout>(R.id.contentLayout).visibility = ConstraintLayout.VISIBLE
        }

        view.findViewById<Button>(R.id.order).setOnClickListener {
            // Send to the online payment activity
            val price : Long = model.price.value as Long
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra("price", price)
            intent.putExtra("brand", model.currentBrand.value?.name as String)
            intent.putExtra("model", model.currentModel.value?.name as String)
            intent.putExtra("version", model.currentVersion.value?.name as String)
            intent.putExtra("options", selectedOptions)
            startActivity(intent)
        }

        return view
    }

    fun adaptSpinner(container: View, spinner : Spinner, list : Array<String?>, placeholder : String?) {
        var array = arrayOf(placeholder)
        for (element in list) {
            array = array.plus(element)
        }
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, array)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent:AdapterView<*>, view: View?, position: Int, id: Long){
                // Get the selected item
                val item : String = parent.getItemAtPosition(position).toString()
                if (item.equals(placeholder)) {
                     if (placeholder.equals("Brand")) {
                         disable(container, 3)
                         model.setBrand(null)
                     }
                     else if (placeholder.equals("Model")) {
                         disable(container, 2)
                         model.setModel(null)
                     }
                     else if (placeholder.equals("Version")) {
                         disable(container, 1)
                         model.setVersion(null)
                     }
                     else if (placeholder.equals("Color")) {
                         disable(container, 0)
                         model.setColor(null)
                     }
                }
                else {
                    //Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                    if (placeholder.equals("Brand")) {
                        model.setBrand(DataManager.instance.findBrand(item))
                    }
                    else if (placeholder.equals("Model")) {
                        model.setModel(model.currentBrand.value?.findModel(item))
                    }
                    else if (placeholder.equals("Version")) {
                        model.setVersion(model.currentModel.value?.findVersion(item))
                    }
                    else if (placeholder.equals("Color")) {
                        model.setColor(DataManager.instance.findColor(item))
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback

            }
        }
    }

    fun fillOptions(parent : LinearLayout, list : Array<String?>) {
        for (element in list) {
            val checkBox : CheckBox = CheckBox(context)
            checkBox.setText(element)
            checkBox.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            parent.addView(checkBox)
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val pr = model.currentVersion.value?.findOption(element)?.price
                    if (pr != null) {
                        model.setOptionsPrice(model.optionsPrice.value?.plus(pr))
                    }
                    selectedOptions.add(element)
                }
                else {
                    val pr = model.currentVersion.value?.findOption(element)?.price
                    if (pr != null) {
                        model.setOptionsPrice(model.optionsPrice.value?.minus(pr))
                    }
                    selectedOptions.remove(element)
                }
            }
        }
    }

    fun setPrice(container : View, price : Long) {
        val priceTag = container.findViewById<TextView>(R.id.priceTag)
        priceTag.setText(price.toString())
    }

    fun disable(container: View, level : Int) {
        model.setColorPrice(0)
        if (level > 0) {
            // Empty Options
            val linearLayout = container.findViewById<LinearLayout>(R.id.options)
            linearLayout.removeAllViews()

            model.setOptionsPrice(0)
            val colors = arrayOf<String?>()
            adaptSpinner(container, container.findViewById(R.id.color), colors, "Color")
            model.setVersionPrice(0)
            if (level > 1) {
                val versions = arrayOf<String?>()
                adaptSpinner(container, container.findViewById(R.id.versionTxt), versions, "Version")
                if (level > 2) {
                    val models = arrayOf<String?>()
                    adaptSpinner(container, container.findViewById(R.id.model), models, "Model")
                }
            }
        }
    }

    fun displayMessage(msg : String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}
