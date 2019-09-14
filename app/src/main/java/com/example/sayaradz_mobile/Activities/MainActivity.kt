package com.example.sayaradz_mobile.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sayaradz_mobile.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val navController = findNavController(R.id.nav_host_fragment)
        //setupActionBarWithNavController(navController)
        nav_view.setupWithNavController(navController)




    }

    override fun onSupportNavigateUp() = findNavController(R.id.homeFragment).navigateUp()



}
