package com.example.sayaradz_mobile.Model

data class Version(var name : String, var price : Long, var options : ArrayList<Option>) {

    fun optionsList() : Array<String?> {
        val array = arrayOfNulls<String>(options.size)
        var index = 0
        for (element in options) {
            array[index] = element.name
            index++
        }
        return array
    }

    fun findOption(option : String?) : Option? {
        for (element in options) {
            if (element.name.equals(option)) {
                return element
            }
        }
        return null
    }

}