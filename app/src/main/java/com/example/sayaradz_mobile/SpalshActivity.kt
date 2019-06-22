package com.example.sayaradz_mobile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SpalshActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent = Intent(this, MainActivity::class.java)
        Thread.sleep(1000)
        startActivity(intent)
        finish()
    }
}
