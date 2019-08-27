package com.example.sayaradz_mobile.Model

data class CarBrand(var name : String, var models : ArrayList<Model>) {

    fun modelsList() : Array<String?> {
        val array = arrayOfNulls<String>(models.size)
        var index = 0
        for (element in models) {
            array[index] = element.name
            index++
        }
        return array
    }

    fun findModel(model : String) : Model? {
        for (element in models) {
            if (element.name.equals(model)) {
                return element
            }
        }
        return null
    }

}