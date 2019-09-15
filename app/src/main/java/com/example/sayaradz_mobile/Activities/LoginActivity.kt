package com.example.sayaradz_mobile.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.sayaradz_mobile.HttpRequests.RestService
import com.example.sayaradz_mobile.HttpRequests.Retrofit
import com.example.sayaradz_mobile.Model.AuthResponse
import com.example.sayaradz_mobile.R
import com.example.sayaradz_mobile.Utils.Utilities
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import com.facebook.CallbackManager
import com.facebook.AccessToken
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class LoginActivity : AppCompatActivity() {



    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager
    private lateinit var gso: GoogleSignInOptions
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Configure Google Sign In
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)


        // Configure Facebook Login
        callbackManager = CallbackManager.Factory.create()


        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.i(TAG,loginResult.accessToken.token)
                    val restService = Retrofit.getRetrofit().create(RestService::class.java)
                    val jsonObject = JsonObject()
                    jsonObject.addProperty("accessToken",loginResult.accessToken.token)
                    compositeDisposable = CompositeDisposable()
                    compositeDisposable?.add(restService.facebookLogin(jsonObject)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse,this::handleError))


                }

                private fun handleResponse(authResponse: AuthResponse){
                    storeAuthResponse(authResponse)
                    skipAuthentication()


                }
                private fun handleError(t:Throwable){
                    Log.e("ERROR",t.message)
                }

                override fun onCancel() {
                    Log.i(TAG,"CANCEL")
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    Log.i(TAG,"ERROR: "+exception.message)
                    // App code
                }
            })


    }

    fun storeAuthResponse(authResponse: AuthResponse){
        val gson = Gson()
        val authResponseString = gson.toJson(authResponse)
        val sharedPref = this.getSharedPreferences("com.example.sayaradz_mobile.AUTH_RESPONSE", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString("authResponse", authResponseString)
            commit()
        }
    }





    fun onGoogleButtonClick(view: View){
        if(Utilities.hasNetwork(this)){
            signIn()
        }else{
            Toast.makeText(this,"Connexion Internet Impossible",Toast.LENGTH_LONG).show()
        }
    }


    fun onFacebookButtonClick(view: View){
        if(Utilities.hasNetwork(this)){
            facebook_login_button.performClick()
        }else{
            Toast.makeText(this,"Connexion Internet Impossible",Toast.LENGTH_LONG).show()
        }


    }

    val RC_SIGN_IN  = 99

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                Log.i(TAG,account!!.idToken)
                authWithGoogle(account!!.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately

                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data)

        }
    }
    // [END onactivityresult]
    val TAG = "ACCOUNT"

    // [START auth_with_google]
    private fun authWithGoogle(idToken:String) {

        val restService = Retrofit.getRetrofit().create(RestService::class.java)
        compositeDisposable = CompositeDisposable()
        val jsonObject = JsonObject()
        jsonObject.addProperty("accessToken",idToken)
        compositeDisposable?.add(restService.googleLogin(jsonObject)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse,this::handleError))


    }
    private fun handleResponse(authResponse: AuthResponse){
        storeAuthResponse(authResponse)
        skipAuthentication()


    }
    private fun handleError(t:Throwable){
        Log.e("ERROR",t.message)
    }

    fun onSkipButtonClick(view: View){
        skipAuthentication()

    }

    private fun skipAuthentication(){
        val intent: Intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }


    fun logOut(){
        val accessToken = AccessToken.getCurrentAccessToken()
        if(accessToken != null && !accessToken.isExpired){
            LoginManager.getInstance().logOut()
            Toast.makeText(this,"Sign Out from Facebook account succeded", Toast.LENGTH_LONG).show()

        }

        if((GoogleSignIn.getLastSignedInAccount(this) != null)){
            googleSignInClient.signOut()
            Toast.makeText(this,"Sign Out from Google account succeded", Toast.LENGTH_LONG).show()
        }

        val intent: Intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 99)
    }



}