package com.example.sayaradz_mobile.Model

import com.example.sayaradz_mobile.Utils.Utilities
import java.io.Serializable
import java.text.SimpleDateFormat


open class Notification : Serializable{
     var title:String = ""
     var description:String = ""
     var photo: String = ""
     var body : NotificationBody? = null
     var unread: Boolean = false
     var date:String = ""


     constructor(notificationBody: NotificationBody)  {
         if (notificationBody.image != null && notificationBody.image != ""){
             photo = notificationBody.image
         }else{
             photo = Utilities.getPhotoUrl()
         }

         body = notificationBody
         unread = notificationBody.unread
         val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
         val formatter = SimpleDateFormat("yyyy HH:mm")
         val monthFormatter = SimpleDateFormat("MM")
         val dayFormatter = SimpleDateFormat("MM")
         date = dayFormatter.format(parser.parse(notificationBody.timestamp))+ " " +
                 Utilities.monthHashMap[(monthFormatter.format(parser.parse(notificationBody.timestamp)).toInt()-1) % 12]+ " " +
                 formatter.format(parser.parse(notificationBody.timestamp))
         when(notificationBody.notification_type){
             "OA"->{
                 //offer accepted
                 title = "Offre Accepté"
                 description = "Votre offre a été accepté par ${notificationBody.actorUserName}"


             }
             "MC"->{
                 //model changed
                 title = "Modèle mis à jour"
                 description = "Les informations du modèle ${notificationBody.verb} ont été changé par le constructeur ${notificationBody.actorUserName}"

             }
             "VC"->{
                 // version changed
                 title = "Version mis à jour"
                 description = "Les informations de la version ${notificationBody.verb} ont été changé par le constructeur ${notificationBody.actorUserName}"

             }
             "CV"->{
                 // command validated
                 title = "Command Validée"
                 description = "Votre commande ${notificationBody.actorTarget}  a été validé par le constructeur ${notificationBody.verb}"


             }
             "OP"->{
                 // offer posted
                 title = "Offre Reçu"
                 description = "Vous avez reçu un offre de ${notificationBody.verb} DZD de la part de ${notificationBody.actorUserName}"


             }
         }

     }

 }