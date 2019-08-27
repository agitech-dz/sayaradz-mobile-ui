package com.example.sayaradz_mobile.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.sayaradz_mobile.R.layout.activity_main)
        supportActionBar?.hide()
        val navController = findNavController(com.example.sayaradz_mobile.R.id.nav_host_fragment)
        //setupActionBarWithNavController(navController)
        nav_view.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp() = findNavController(com.example.sayaradz_mobile.R.id.homeFragment).navigateUp()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }








}
