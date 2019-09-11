package com.example.sayaradz_mobile.Model

class Supply {

    val vehicles : ArrayList<Vehicle> = ArrayList()

    fun fillSupply() {
        // Fill some insatances of vehicle
        val op1 = ArrayList<String?>()
        val op2 = ArrayList<String?>()
        vehicles.add(Vehicle(1, "Renault", "Sandero", "StepWay", "White", op1))
        op2.add("Climatiseur")
        op2.add("Boite Automatique")
        op2.add("ABS")
        vehicles.add(Vehicle(2, "Audi", "Audi Q5", "Audi Q5 TT", "Blue", op2))
    }

    fun findVehicle(brand : String?, model : String?, version : String?, color : String?, options : ArrayList<String?>) : Boolean {
        val temp = Vehicle(0, brand, model, version, color, options)
        for (element in vehicles) {
            if (element.equals(temp)) {
                return true
            }
        }
        return false
    }

}