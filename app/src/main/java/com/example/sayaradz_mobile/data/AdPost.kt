package com.example.sayaradz_mobile.data

import retrofit2.http.Multipart


data class AdPost(
              var model:String,
              var version:String,
              var manufacturer: String,
              var year: String,
              var distance: String,
              var description: String,
              var automobilist: String,
              var minPrice: String,
              var energy: String,
              var personsNumber: Int
)