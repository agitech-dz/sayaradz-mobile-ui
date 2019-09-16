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
import com.example.sayaradz_mobile.Data.*
import com.example.sayaradz_mobile.Interface.GetData
import com.example.sayaradz_mobile.Model.*
import com.example.sayaradz_mobile.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class GarageFragment : Fragment() {

    // The API Data
    private val BASE_URL = "http://sayaradz-back-end.herokuapp.com/"
    private var compositeDisposable: CompositeDisposable? = null

    var brandsList: ArrayList<BrandModel>? = null
    var modelsList: ArrayList<ModelModel>? = null
    var versionsList: ArrayList<VersionModel>? = null
    var colorsList: ArrayList<ColorModel>? = null
    var optionsList: ArrayList<OptionModel>? = null

    companion object {
        val instance = GarageFragment()
    }

    lateinit var model : CurrentData // ViewModel to hold the current choices made by the user
    val selectedOptions : ArrayList<String?> = ArrayList() // Add Selected Options List

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_garage, container, false)

        model = ViewModelProviders.of(this).get(CurrentData::class.java)

        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE

        /***** Implement Observers *****/
        val brandObserver = Observer<BrandModel?> {newBrand ->
            if (newBrand != null) {
                loadModels()
                enableDisplay(2)
            }
            else {
                disableDisplay(2)
            }
        }

        val modelObserver = Observer<ModelModel?> {newModel ->
            if (newModel != null) {
                loadVersions()
                enableDisplay(1)
            }
            else {
                disableDisplay(1)
            }
        }

        val versionObserver = Observer<VersionModel?> {newVersion ->
            if (newVersion != null) {
                loadColors()
                loadOptions()
                model.setVersionPrice(newVersion.tarifPrice)
                enableDisplay(0)
            }
            else {
                disableDisplay(0)
            }
        }

        val colorObserver = Observer<ColorModel?> {newColor ->
            if (newColor != null) {
                model.setColorPrice(newColor.tarifPrice)
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
                search()
                // Display Progressbar
                view.findViewById<ConstraintLayout>(R.id.contentLayout).visibility = ConstraintLayout.INVISIBLE
                view.findViewById<ConstraintLayout>(R.id.cover).visibility = ConstraintLayout.VISIBLE
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            }
        }

        view.findViewById<ImageView>(R.id.close).setOnClickListener {
            view.findViewById<ConstraintLayout>(R.id.popupFail).visibility = ConstraintLayout.GONE
            view.findViewById<ConstraintLayout>(R.id.cover).visibility = ConstraintLayout.GONE
            view.findViewById<ConstraintLayout>(R.id.contentLayout).visibility = ConstraintLayout.VISIBLE
        }
        view.findViewById<ImageView>(R.id.close2).setOnClickListener {
            view.findViewById<ConstraintLayout>(R.id.popupSuccess).visibility = ConstraintLayout.GONE
            view.findViewById<ConstraintLayout>(R.id.cover).visibility = ConstraintLayout.GONE
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

        loadBrands()

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

    fun adaptSpinner(container: View, spinner : Spinner, list : Array<String?>, placeholder : String?) {
        var array = arrayOf(placeholder)
        for (element in list) {
            array = array.plus(element)
        }
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, array)
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
                        model.setBrand(DataManager.instance.searchBrand(brandsList, item))
                    }
                    else if (placeholder.equals("Model")) {
                        model.setModel(DataManager.instance.searchModel(modelsList, item))
                    }
                    else if (placeholder.equals("Version")) {
                        model.setVersion(DataManager.instance.searchVersion(versionsList, item))
                    }
                    else if (placeholder.equals("Color")) {
                        model.setColor(DataManager.instance.searchColor(colorsList, item))
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
                    val pr = DataManager.instance.searchOption(optionsList, element!!)?.tarifPrice
                    if (pr != null) {
                        model.setOptionsPrice(model.optionsPrice.value?.plus(pr))
                    }
                    selectedOptions.add(element)
                }
                else {
                    val pr = DataManager.instance.searchOption(optionsList, element!!)?.tarifPrice
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

    fun loadBrands() {
        compositeDisposable = CompositeDisposable()
        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetData::class.java)
        compositeDisposable?.add(requestInterface.getBrands()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleBrandResponse))
    }

    fun handleBrandResponse(listOfBrands: List<BrandModel>) {
        brandsList = ArrayList(listOfBrands)
        adaptSpinner(view!!,
                view!!.findViewById(R.id.marque),
                DataManager.instance.convertBrandsToArray(brandsList),
                "Brand")
    }

    fun loadModels() {
        compositeDisposable = CompositeDisposable()
        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetData::class.java)
        compositeDisposable?.add(requestInterface.getModels(model.currentBrand.value!!.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleModelResponse))
    }

    fun handleModelResponse(listOfModels: List<ModelModel>) {
        modelsList = ArrayList(listOfModels)
        adaptSpinner(view!!,
                view!!.findViewById(R.id.model),
                DataManager.instance.convertModelsToArray(modelsList),
                "Model")
    }

    fun loadVersions() {
        compositeDisposable = CompositeDisposable()
        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetData::class.java)
        compositeDisposable?.add(requestInterface.getVersions(model.currentModel.value!!.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleVersionResponse))
    }

    fun handleVersionResponse(listOfVersions: List<VersionModel>) {
        versionsList = ArrayList(listOfVersions)
        adaptSpinner(view!!,
                view!!.findViewById(R.id.versionTxt),
                DataManager.instance.convertVersionsToArray(versionsList),
                "Version")
    }

    fun loadColors() {
        compositeDisposable = CompositeDisposable()
        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetData::class.java)
        compositeDisposable?.add(requestInterface.getColors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleColorsResponse))
    }

    fun handleColorsResponse(listOfColors: List<ColorModel>) {
        colorsList = ArrayList(listOfColors)
        adaptSpinner(view!!,
                view!!.findViewById(R.id.color),
                DataManager.instance.convertColorsToArray(colorsList),
                "Color")
    }

    fun loadOptions() {
        compositeDisposable = CompositeDisposable()
        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetData::class.java)
        compositeDisposable?.add(requestInterface.getOptions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleOptionsResponse))
    }

    fun handleOptionsResponse(listOfOptions: List<OptionModel>) {
        optionsList = ArrayList(listOfOptions)
        fillOptions(view!!.findViewById(R.id.options), DataManager.instance.convertOptionsToArray(optionsList))
    }

    fun disableDisplay(level: Int) {
        view!!.findViewById<Spinner>(R.id.color).isEnabled = false
        if (level > 0) {
            view!!.findViewById<Spinner>(R.id.versionTxt).isEnabled = false
            if (level > 1) {
                view!!.findViewById<Spinner>(R.id.model).isEnabled = false
            }
        }
    }

    fun enableDisplay(level: Int) {
        when (level) {
            0 -> {
                view!!.findViewById<Spinner>(R.id.color).isEnabled = true
            }
            1 -> {
                view!!.findViewById<Spinner>(R.id.versionTxt).isEnabled = true
            }
            2 -> {
                view!!.findViewById<Spinner>(R.id.model).isEnabled = true
            }
        }
    }

    fun search() {
        val obj = SearchInput(model.currentVersion.value!!.id,
                model.currentColor.value!!.id,
                DataManager.instance.getOptionsIds(optionsList, selectedOptions))
        compositeDisposable = CompositeDisposable()
        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetData::class.java)
        compositeDisposable?.add(requestInterface.searchVehicle(obj)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::searchResult))
    }

    fun searchResult(result: SearchOutput) {
        view!!.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        if (result.countFound > 0) {
            view!!.findViewById<ConstraintLayout>(R.id.popupSuccess).visibility = ConstraintLayout.VISIBLE
        }
        else {
            view!!.findViewById<ConstraintLayout>(R.id.popupFail).visibility = ConstraintLayout.VISIBLE
        }
    }

}


