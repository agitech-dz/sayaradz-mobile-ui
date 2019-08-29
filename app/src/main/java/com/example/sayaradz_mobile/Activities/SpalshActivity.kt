package com.example.sayaradz_mobile.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sayaradz_mobile.R

class SpalshActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent = Intent(this, Main2Activity::class.java)
        Thread.sleep(2000)
        startActivity(intent)
        finish()
    }
}
