package com.example.sayaradz_mobile.Model

import com.example.sayaradz_mobile.Data.*
import kotlin.collections.ArrayList

class DataManager {

    companion object {
        val instance = DataManager()
    }

    fun searchBrand(list: ArrayList<BrandModel>?, item: String): BrandModel? {
        for (element in list!!) {
            if (element.name.equals(item)) {
                return element
            }
        }
        return null
    }

    fun searchModel(list: ArrayList<ModelModel>?, item: String): ModelModel? {
        for (element in list!!) {
            if (element.name.equals(item)) {
                return element
            }
        }
        return null
    }

    fun searchVersion(list: ArrayList<VersionModel>?, item: String): VersionModel? {
        for (element in list!!) {
            if (element.name.equals(item)) {
                return element
            }
        }
        return null
    }

    fun searchColor(list: ArrayList<ColorModel>?, item: String): ColorModel? {
        for (element in list!!) {
            if (element.name.equals(item)) {
                return element
            }
        }
        return null
    }

    fun searchOption(list: ArrayList<OptionModel>?, item: String): OptionModel? {
        for (element in list!!) {
            if (element.name.equals(item)) {
                return element
            }
        }
        return null
    }

    fun convertBrandsToArray(list: ArrayList<BrandModel>?): Array<String?> {
        val result = arrayOfNulls<String>(list!!.size)
        var index = 0
        for (element in list) {
            result[index] = element.name
            index++
        }
        return result
    }

    fun convertModelsToArray(list: ArrayList<ModelModel>?): Array<String?> {
        val result = arrayOfNulls<String>(list!!.size)
        var index = 0
        for (element in list) {
            result[index] = element.name
            index++
        }
        return result
    }

    fun convertVersionsToArray(list: ArrayList<VersionModel>?): Array<String?> {
        val result = arrayOfNulls<String>(list!!.size)
        var index = 0
        for (element in list) {
            result[index] = element.name
            index++
        }
        return result
    }

    fun convertColorsToArray(list: ArrayList<ColorModel>?): Array<String?> {
        val result = arrayOfNulls<String>(list!!.size)
        var index = 0
        for (element in list) {
            result[index] = element.name
            index++
        }
        return result
    }

    fun convertOptionsToArray(list: ArrayList<OptionModel>?): Array<String?> {
        val result = arrayOfNulls<String>(list!!.size)
        var index = 0
        for (element in list) {
            result[index] = element.name
            index++
        }
        return result
    }

    fun getOptionsIds(list: ArrayList<OptionModel>?, options: ArrayList<String?>): List<String> {
        val result = ArrayList<String>()
        for (option in options) {
            for (e in list!!) {
                if (option == e.name) {
                    result.add(e.id)
                    break
                }
            }
        }
        return result
    }

}