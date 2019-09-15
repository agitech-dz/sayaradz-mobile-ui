package com.example.sayaradz_mobile.Model

data class NotificationBody (
    val id: Int,
    val actor: String,
    val actorUserName: String,
    val actorEmail: String,
    val actorTelephone: String,
    val actorTarget: String,
    val recipient: Int,
    val verb: String,
    val timestamp: String,
    var unread: Boolean,
    val notification_type: String,
    val image: String,
    val actionObject:String
)