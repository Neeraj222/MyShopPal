package com.example.myshoppal.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myshoppal.R
import kotlinx.android.synthetic.main.activity_add_edit_address.*

class AddEditAddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_address)
        setupActionBar()
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_add_edit_address_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbar_add_edit_address_activity.setNavigationOnClickListener { onBackPressed() }
    }
    // END
}
// END