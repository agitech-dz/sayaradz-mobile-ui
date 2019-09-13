package com.example.sayaradz_mobile.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.sayaradz_mobile.R
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()
        val navController = findNavController(R.id.nav_host_fragment)
        //setupActionBarWithNavController(navController)
        nav_view.setupWithNavController(navController)




    }

    override fun onSupportNavigateUp() = findNavController(R.id.homeFragment).navigateUp()



}
