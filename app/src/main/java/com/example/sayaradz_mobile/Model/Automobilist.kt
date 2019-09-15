package com.example.sayaradz_mobile.Model

data class Automobilist (
    val id: Int,
    val first_name: String,
    val last_name: String,
    val username: String,
    private val password: String,
    val address: String,
    val telephone: String,
    val profile_image: String
)