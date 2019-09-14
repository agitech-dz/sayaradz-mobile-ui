package com.example.sayaradz_mobile.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sayaradz_mobile.R
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn

class SpalshActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = (accessToken != null && !accessToken.isExpired) || (GoogleSignIn.getLastSignedInAccount(this) != null)
        if(isLoggedIn){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
