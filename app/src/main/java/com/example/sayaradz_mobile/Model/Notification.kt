package com.example.sayaradz_mobile.Model

import java.io.Serializable
 open class Notification : Serializable{
     var title:String = ""
     var description:String = ""
     var photo: String = ""
     var body : Notification? = null

     constructor()

     constructor(offerNotification: OfferNotification)  {
         title = "Offer"
         description = "You have a new offer from ${offerNotification.actorUserName}"
         photo = "https://www.taymorplumbingsupplies.co.uk/wp-content/uploads/2016/07/Special-Offer.png"
         body = offerNotification
     }

     constructor(commandNotification: CommandNotification)  {
         title = "Command"
         description = "Your command for the ${commandNotification.commandCar} car from ${commandNotification.manufacturer} is approved"
         photo = "https://www.appliancesdirect.co.uk/files/images/apd/apd-next-day.svg"
         body = commandNotification
     }
 }