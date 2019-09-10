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
) : Notification( "Command", "Your command for $commandCar is ready!","https://the-drive.imgix.net/http%3A%2F%2Fd254andzyoxz3f.cloudfront.net%2Fcrictics-notebook-tesla-s-p90d-ludicrous-hero.jpg?w=1440&auto=compress%2Cformat&ixlib=js-1.4.1&s=08e88bf9c1aa3e82706feaa8ad106e52")