package com.example.sayaradz_mobile.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sayaradz_mobile.R
import kotlinx.android.synthetic.main.activity_confirmation.*
import org.json.JSONException
import org.json.JSONObject

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        try {
            val jsonDetails : JSONObject = JSONObject(intent.getStringExtra("PaymentDetails"))
            showDetails(jsonDetails.getJSONObject("response"), intent.getLongExtra("PaymentAmount", 0).toString())
        }
        catch (e: JSONException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

        goBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showDetails(jsonDetails: JSONObject, paymentAmount: String) {
        paymentId.text = jsonDetails.getString("id")
        paymentStatus.text = jsonDetails.getString("state")
        paymentAmounts.text = paymentAmount + " USD"
    }
}
