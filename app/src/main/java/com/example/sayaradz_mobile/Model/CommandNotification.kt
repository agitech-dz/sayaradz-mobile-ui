package com.example.sayaradz_mobile.Model

data class CommandNotification(
    val id: Int,
    val command: String,
    val recipient: Int,
    val manufacturer: String,
    val commandCar: String,
    val commandTotal: Int,
    val commandDate: String,
    val timestamp: String,
    val unread: Boolean,
    val notification_type: String
)