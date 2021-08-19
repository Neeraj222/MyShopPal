package com.example.myshoppal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myshoppal.R
import kotlinx.android.synthetic.main.activity_checkout.*

// TODO Step 1: Create a CheckoutActivity.
// START
/**
 * A CheckOut activity screen.
 */
class CheckoutActivity : AppCompatActivity() {

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_checkout)

        // TODO Step 7: Call the function to setup the action bar.
        // START
        setupActionBar()
        // END
    }

    // TODO Step 6: Create a function to setup the action bar.
    // START
    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_checkout_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbar_checkout_activity.setNavigationOnClickListener { onBackPressed() }
    }
    // END
}