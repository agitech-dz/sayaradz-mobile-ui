package com.example.sayaradz_mobile.Model

import java.io.Serializable

open class Notification(
    open val id: Int,
    var title:String,
    var description:String,
    open val notification_type: String,
    var photo: String = ""
): Serializable