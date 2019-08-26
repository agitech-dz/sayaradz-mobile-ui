package com.example.sayaradz_mobile.utils

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.example.sayaradz_mobile.data.Manufacturer
import com.example.sayaradz_mobile.utils.extensions.SpinnerExtensions
import com.example.sayaradz_mobile.utils.extensions.SpinnerExtensions.setSpinnerEntries
import com.example.sayaradz_mobile.utils.extensions.SpinnerExtensions.setSpinnerItemSelectedListener
import com.example.sayaradz_mobile.utils.extensions.SpinnerExtensions.setSpinnerValue

@BindingAdapter("entries")
fun Spinner.setEntries(entries: List<Manufacturer>?) {
    setSpinnerEntries(entries)
}




