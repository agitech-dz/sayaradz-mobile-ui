package com.example.sayaradz_mobile.Model

data class CommandNotification(
    override val id: Int,
    val command: String,
    val recipient: Int,
    val manufacturer: String,
    val commandCar: String,
    val commandTotal: Int,
    val commandDate: String,
    val timestamp: String,
    val unread: Boolean,
    override val notification_type: String
): Notification(id, "Command", "Your command for $commandCar is ready!",notification_type)