package com.example.sayaradz_mobile.Model

import java.io.Serializable

data class AuthResponse(

    val auth_token: String,
    val new_automobilist: Boolean,
    val automobilist_id: Int
): Serializable