package com.example.myshoppal.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myshoppal.R
import com.example.myshoppal.models.Address
import com.example.myshoppal.utils.Constants
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {

    private var mAddressDetails: Address? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_checkout)
        setupActionBar()


        if (intent.hasExtra(Constants.EXTRA_SELECT_ADDRESS)) {
            mAddressDetails =
                intent.getParcelableExtra<Address>(Constants.EXTRA_SELECT_ADDRESS)!!
        }

        if (mAddressDetails != null) {
            tv_checkout_address_type.text = mAddressDetails?.type
            tv_checkout_full_name.text = mAddressDetails?.name
            tv_checkout_address.text = "${mAddressDetails!!.address}, ${mAddressDetails!!.zipCode}"
            tv_checkout_additional_note.text = mAddressDetails?.additionalNote

            if (mAddressDetails?.otherDetails!!.isNotEmpty()) {
                tv_checkout_other_details.text = mAddressDetails?.otherDetails
            }
            tv_mobile_number.text = mAddressDetails?.mobileNumber
        }
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_checkout_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbar_checkout_activity.setNavigationOnClickListener { onBackPressed() }
    }
}