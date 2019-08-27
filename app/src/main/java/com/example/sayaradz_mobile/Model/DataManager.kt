package com.example.sayaradz_mobile.Model

class DataManager {

    companion object {
        val instance = DataManager()
    }

    lateinit var colors : ArrayList<Color>
    lateinit var brands : ArrayList<CarBrand>

    fun init() {
        colors = ArrayList()
        brands = ArrayList()

        // Instanciate Colors
        colors.add(Color("White", 50000))
        colors.add(Color("Blue", 30000))
        colors.add(Color("Grey", 20000))
        colors.add(Color("Black", 10000))

        // Instanciate Brands
        // Les Options
        val options = ArrayList<Option>()
        options.add(Option("Climatiseur", 100000))
        options.add(Option("Vitre Electrique", 20000))
        options.add(Option("ABS", 30000))
        options.add(Option("Anti-Dérapage", 40000))
        options.add(Option("Toit Ouvrant", 150000))
        options.add(Option("Caméra de Recul", 20000))
        options.add(Option("Boite Automatique", 100000))

        // Les Versions
        val v1 = ArrayList<Version>()
        v1.add(Version("StepWay", 3000000, options))
        v1.add(Version("Twingo II", 2700000, options))

        val v2 = ArrayList<Version>()
        v2.add(Version("Laguna III", 2750000, options))
        v2.add(Version("Koleos", 2840000, options))

        val v3 = ArrayList<Version>()
        v3.add(Version("Audi A1 RS", 3000000, options))
        v3.add(Version("Audi A1 TDI", 3800000, options))

        val v4 = ArrayList<Version>()
        v4.add(Version("Audi R8 RS", 2850000, options))
        v4.add(Version("Audi R8 GT", 2950000, options))

        val v5 = ArrayList<Version>()
        v5.add(Version("Audi Q5 RS", 5000000, options))
        v5.add(Version("Audi Q5 TT", 5500000, options))

        // Les Models
        val m1 = ArrayList<Model>()
        m1.add(Model("Sandero", v1))
        m1.add(Model("Laguna", v2))

        val m2 = ArrayList<Model>()
        m2.add(Model("Audi A1", v3))
        m2.add(Model("Audi R8", v4))
        m2.add(Model("Audi Q5", v5))

        // Les Marques
        brands.add(CarBrand("Renault", m1))
        brands.add(CarBrand("Audi", m2))

    }

    fun brandsList() : Array<String?> {
        val array = arrayOfNulls<String>(brands.size)
        var index = 0
        for (element in brands) {
            array[index] = element.name
            index++
        }
        return array
    }

    fun colorsList() : Array<String?> {
        val array = arrayOfNulls<String>(colors.size)
        var index = 0
        for (element in colors) {
            array[index] = element.name
            index++
        }
        return array
    }

    fun findBrand(brand : String) : CarBrand? {
        for (element in brands) {
            if (element.name.equals(brand)) {
                return element
            }
        }
        return null
    }

    fun findColor(color : String) : Color? {
        for (element in colors) {
            if (element.name.equals(color)) {
                return element
            }
        }
        return null
    }

}