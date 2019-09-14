package com.example.sayaradz_mobile.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.sayaradz_mobile.R
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


class LoginActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager
    private lateinit var gso: GoogleSignInOptions

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
                    skipAuthentication()
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

    fun onGoogleButtonClick(view: View){
        signIn()
    }


    fun onFacebookButtonClick(view: View){
        facebook_login_button.performClick()

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
                firebaseAuthWithGoogle()
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
    private fun firebaseAuthWithGoogle() {
        skipAuthentication()

//        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    val user = auth.currentUser
//                    Toast.makeText(applicationContext,"Bonjour ${user!!.displayName}", Toast.LENGTH_LONG)
//
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Toast.makeText(applicationContext,"Sign in Failed", Toast.LENGTH_LONG)
//                }
//
//                // [START_EXCLUDE]
//
//                // [END_EXCLUDE]
//            }
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