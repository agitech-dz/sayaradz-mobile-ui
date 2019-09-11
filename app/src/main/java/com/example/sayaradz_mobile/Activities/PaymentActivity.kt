package com.example.sayaradz_mobile.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.util.TypedValue
import android.widget.TextView
import com.example.sayaradz_mobile.Model.PayPalConfig
import com.example.sayaradz_mobile.R
import com.paypal.android.sdk.payments.*
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_payment.*
import org.json.JSONException
import java.math.BigDecimal

class PaymentActivity : AppCompatActivity() {

    //Paypal intent request code to track onActivityResult method
    val PAYPAL_REQUEST_CODE : Int = 101

    //Payment Amount
    var price: Long = 0
    //Other info
    var brand: String = ""
    var model: String = ""
    var version: String = ""
    var options: ArrayList<String?> = ArrayList()

    //Paypal Configuration Object, Test on Sandbox and then switch to Production
    private val config : PayPalConfiguration = PayPalConfiguration().
                                               environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).
                                               clientId(PayPalConfig().PAYPAL_CLIENT_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // Getting the price from previous activity
        price = intent.getLongExtra("price", 0) / 100 /** Make the price smaller to have healthy balance **/
        brand = intent.getStringExtra("brand")
        model = intent.getStringExtra("model")
        version = intent.getStringExtra("version")
        options = intent.getStringArrayListExtra("options")

        // Update interface
        priceTag.text = price.toString() + " USD"
        versionTxt.text = version
        modelTxt.text = model
        brandTxt.text = brand
        // Add the options
        for (element in options) {
            val textView = TextView(this)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
            textView.text = "  " + Html.fromHtml("&#8226;") + "  " + element
            optionsContainer.addView(textView)
        }

        returnButton.setOnClickListener {
            super.onBackPressed()
            finish()
        }

        button.setOnClickListener {

            // Creating a palpalpayment
            val payment = PayPalPayment(BigDecimal(price), "USD", "Vehicle Price", PayPalPayment.PAYMENT_INTENT_SALE)

            //Creating Paypal Payment activity intent
            val intent = Intent(this, com.paypal.android.sdk.payments.PaymentActivity::class.java)

            //putting the paypal configuration to the intent
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)

            //Puting paypal payment to the intent
            intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, payment)

            //Starting the intent activity for result
            //the request code will be used on the method onActivityResult
            startActivityForResult(intent, PAYPAL_REQUEST_CODE)
        }

        val intent : Intent = Intent(this, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(intent)

    }

    override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {

                //Getting the payment confirmation
                val confirm: PaymentConfirmation? = data?.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)

                //if confirmation is not null
                if (confirm != null) {
                    try {

                        //Getting the payment details
                        val paymentDetails: String = confirm.toJSONObject().toString(4)
                        Log.i("payment", paymentDetails)

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        startActivity(Intent(this, ConfirmationActivity::class.java).
                                      putExtra("PaymentDetails", paymentDetails).
                                      putExtra("PaymentAmount", price))
                    }
                    catch (e: JSONException) {
                        Log.e("payment", "an extremely unlikely failure occurred: ", e)
                    }
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("payment", "The user canceled.")
            }
            else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("payment", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.")
            }
        }
    }

}
