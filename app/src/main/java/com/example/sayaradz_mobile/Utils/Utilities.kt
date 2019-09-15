package com.example.sayaradz_mobile.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import kotlin.random.Random

object Utilities {

    val monthHashMap = arrayOf("Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Décembre")


    fun hasNetwork(context: Context): Boolean {
        var isConnected: Boolean = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork!!.isConnected)
            isConnected = true
        return isConnected
    }

    fun getPhotoUrl(): String{
        val photoUrls: ArrayList<String> = arrayListOf(
            "https://www.sciencesetavenir.fr/assets/img/2018/09/06/cover-r4x3w1000-5b91612a81afc-section-hero-background.jpg",
            "https://www.teslarati.com/wp-content/uploads/2019/06/tesla-model-s-refresh-concept.jpg",
            "https://st.motortrend.com/uploads/sites/10/2015/11/2015-tesla-model-s-sedan-angular-front.png",
            "https://img.autoplus.fr/news/2019/04/24/1537947/1200%7C800%7C1af8c7e95f9df8de31cbc146.jpg",
            "https://cdn.motor1.com/images/mgl/OjmPo/s1/tesla-model-s-render.jpg",
            "https://img.autoplus.fr/picture/tesla-motors/model-s/1503606/Tesla_Motors_Model_S_2016_44c4a-1200-800.jpg",
            "https://www.forbes.fr/wp-content/uploads/2019/09/tesla-sans-cuir-740x370.jpg",
            "https://www.protegez-vous.ca/var/protegez_vous/storage/images/_aliases/width_900px/medias/galerie-photo/tesla-model-3/1936/2715617-1-fre-CA/1936.jpg",
            "https://images.sudouest.fr/2017/04/17/58f4872c66a4bdfc5a1d122c/widescreen/1000x500/la-derniere-version-de-la-tesla-propose-une-face-avant-plus-elegante.jpg",
            "https://www.largus.fr/images/images/tesla-model-s_1.jpg?width=612&quality=80",
            "https://cdn.shopify.com/s/files/1/0043/8471/8938/products/155674392269619844_812x.jpg?v=1556743983",
            "https://www.challenges.fr/assets/img/2017/08/07/cover-r4x3w1000-5b1aa348121e9-tesla-model-3-73-jpeg.jpg",
            "https://the-drive.imgix.net/http%3A%2F%2Fd254andzyoxz3f.cloudfront.net%2Fcrictics-notebook-tesla-s-p90d-ludicrous-hero.jpg?w=1440&auto=compress%2Cformat&ixlib=js-1.4.1&s=08e88bf9c1aa3e82706feaa8ad106e52"

        )

        return photoUrls.get(Random.nextInt(0,photoUrls.count()))
    }
}