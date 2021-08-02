package com.example.myshoppal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.myshoppal.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)




        val btnSubmit = findViewById<TextView>(R.id.btn_submit)
        btnSubmit.setOnClickListener {
            // Get the email id from the input field.
            val etEmail = findViewById<TextView>(R.id.et_email).toString().trim() { it <= ' ' }
            if(etEmail.isEmpty()){
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            }else{
                showProgressDialog(resources.getString(R.string.please_wait))
                FirebaseAuth.getInstance().sendPasswordResetEmail(etEmail)
                    .addOnCompleteListener{
                        task ->
                        hideProgressDialog()

                if (task.isSuccessful) {
                    // Show the toast message and finish the forgot password activity to go back to the login screen.
                    Toast.makeText(
                        this@ForgotPassword,
                        resources.getString(R.string.email_sent_success),
                        Toast.LENGTH_LONG
                    ).show()

                    finish()
                } else {
                    showErrorSnackBar(task.exception!!.message.toString(), true)
                }
            }
            }

        }

    }
//    private fun setupActionBar() {
//
//        setSupportActionBar(toolbar_forgot_password_activity)
//
//        val actionBar = supportActionBar
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true)
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24)
//        }
//
//        toolbar_forgot_password_activity.setNavigationOnClickListener { onBackPressed() }
//    }
}