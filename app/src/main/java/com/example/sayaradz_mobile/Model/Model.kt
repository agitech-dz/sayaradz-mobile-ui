package com.example.sayaradz_mobile.Model

data class Model(var name : String, var versions : ArrayList<Version>) {

    fun versionsList() : Array<String?> {
        val array = arrayOfNulls<String>(versions.size)
        var index = 0
        for (element in versions) {
            array[index] = element.name
            index++
        }
        return array
    }

    fun findVersion(version : String) : Version? {
        for (element in versions) {
            if (element.name.equals(version)) {
                return element
            }
        }
        return null
    }

}