package com.example.myshoppal.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myshoppal.R
import com.example.myshoppal.utils.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences(Constants.MYSHOP_PREFERECNES, Context.MODE_PRIVATE)
        val username =  sharedPreferences.getString(Constants.LOGGED_IN_USER, "")!!

        val tvMain = findViewById<TextView>(R.id.tvMain)
        tvMain.text= "Hello $username."

    }
}