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
            "http://travelagent.ie/wp-content/uploads/2017/05/Car-Image.jpg",
            "https://sx-content-labs.sixt.io/thirdlight/seo/branches/content_ustates_190501_ford_mustang_shelby_20_35.jpg",
            "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAElkil.img?h=426&w=624&m=6&q=60&u=t&o=t&l=f&x=619&y=450",
            "https://cdn.gearpatrol.com/wp-content/uploads/2018/03/15-of-the-Worst-Used-Cars-gear-patrol-lead-full.jpg",
            "http://img.timeinc.net/time/photoessays/2008/10_cars/kitt.jpg",
            "http://stat.overdrive.in/wp-content/uploads/2018/07/2019-Audi-Q3-SUV.jpg",
            "https://focus2move.com/wp-content/uploads/2019/03/Ford-Mustang_Shelby_GT500-2020-680x340.jpg",
            "https://www.classiccarsfriesland.com/wp-content/uploads/2018/12/ccf32.jpg",
            "https://cartrack.co.za/sites/default/files/car.jpg",
            "https://www.mercedes-benz.com/wp-content/uploads/sites/3/2018/02/mercedes-benz-passenger-cars-2018-a-class-w-177-amg-line-digital-white-pearl-2560x1440-2560x1440.jpg",
            "https://media.wired.com/photos/5a207b8740ed7f46060c7dda/master/w_2560%2Cc_limit/lucid-roundup-TA.jpg",
            "https://images.unsplash.com/photo-1532974297617-c0f05fe48bff?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80",
            "https://static.posters.cz/image/1300/fotobehang/vintage-car-cuba-havana-416x254-cm-premium-non-woven-130gsm-i68737.jpg",
            "https://cdn.pixabay.com/photo/2019/07/07/14/03/fiat-4322521__340.jpg",
            "https://cmsimages-alt.kbb.com/content/dam/kbb-editorial/make/rolls-royce/cullinan/2019-rolls-royce-cullinan-side.jpg/_jcr_content/renditions/cq5dam.web.1280.1280.jpeg",
            "https://images.pexels.com/photos/170811/pexels-photo-170811.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
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