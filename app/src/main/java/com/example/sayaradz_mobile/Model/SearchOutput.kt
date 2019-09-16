package com.example.sayaradz_mobile.Model

import com.google.gson.annotations.SerializedName

class SearchOutput (@SerializedName("count_found") val countFound: Int,
                    @SerializedName("found_cars") val foundCards: List<NewCar>,
                    @SerializedName("recommended_cars") val recommendedCars: List<NewCar>
                    )