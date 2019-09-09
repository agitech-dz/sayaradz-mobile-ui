package com.example.sayaradz_mobile.Model

data class OfferNotification (
    override val id: Int,
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
    override val notification_type: String
): Notification(id,"Offer", "You have got an offer from $actorUserName",notification_type)