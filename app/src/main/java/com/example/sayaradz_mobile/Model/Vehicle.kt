package com.example.sayaradz_mobile.Model

data class Vehicle(val id : Long, val brand : String?, val model : String?, val version : String?, val color: String?, val options : ArrayList<String?>) {

    override fun equals(other: Any?): Boolean {
        val obj = other as Vehicle
        if (!brand.equals(obj.brand) || !model.equals(obj.model) || !version.equals(obj.version) || !color.equals(obj.color)) {
            return false
        }
        val list = ArrayList<String?>(obj.options)
        for (element in options) {
            if (!list.contains(element)) {
                return false
            }
            list.remove(element)
        }
        return list.size == 0
    }

}