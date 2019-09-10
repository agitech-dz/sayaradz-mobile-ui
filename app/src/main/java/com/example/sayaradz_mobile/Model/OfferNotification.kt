package com.example.sayaradz_mobile.Model

data class OfferNotification (
    val id: Int,
    val offer: Int,
    val actor: String,
    val actorUserName: String,
    val actorEmail: String,
    val actorTelephone: String,
    val actorTarget: String,
    val recipient: Int,
    val verb: String,
    val timestamp: String,
    val unread: Boolean,
    val notification_type: String
): Notification("Offer", "You have got an offer from $actorUserName","https://cdn.motor1.com/images/mgl/OjmPo/s1/tesla-model-s-render.jpg")