package com.example.myshoppal.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myshoppal.R
import kotlinx.android.synthetic.main.activity_address_list.*

class AddressListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)
        setupActionBar()

    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_address_list_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbar_address_list_activity.setNavigationOnClickListener { onBackPressed() }
    }

}
