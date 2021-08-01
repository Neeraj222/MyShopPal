package com.example.myshoppal.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.TextView
import com.example.myshoppal.R


@Suppress("DEPRECATION")
class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        setupActionBar()

        val btnRegister = findViewById<TextView>(R.id.btn_register)
        btnRegister.setOnClickListener {

            validateRegisterDetails()
        }
        // END

        val btnLogin = findViewById<TextView>(R.id.tv_login)
        btnLogin.setOnClickListener(){
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

//    private fun setupActionBar() {
//
//        setSupportActionBar(toolbar_register_activity)
//
//        val actionBar = supportActionBar
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true)
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
//        }
//
//        toolbar_register_activity.setNavigationOnClickListener { onBackPressed() }
//    }

    private fun validateRegisterDetails(): Boolean {
        val firstName = findViewById<TextView>(R.id.et_first_name)
        val lastName = findViewById<TextView>(R.id.et_last_name)
        val email = findViewById<TextView>(R.id.et_email)
        val password = findViewById<TextView>(R.id.et_password)
        val confirmPassword = findViewById<TextView>(R.id.et_confirm_password)
        val termsAndCondition = findViewById<TextView>(R.id.cb_terms_and_condition)
        return when {
            TextUtils.isEmpty(firstName.text.toString()) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }

            TextUtils.isEmpty(lastName.text.toString()) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }

            TextUtils.isEmpty(email.text.toString()) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(password.text.toString()) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            TextUtils.isEmpty(confirmPassword.text.toString()) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_confirm_password), true)
                false
            }

            password.text.toString() != confirmPassword.text.toString()
                 -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_and_confirm_password_mismatch), true)
                false
            }
//            !termsAndCondition.isChecked -> {
//                showErrorSnackBar(resources.getString(R.string.err_msg_agree_terms_and_condition), true)
//                false
//            }

            else -> {
                showErrorSnackBar(resources.getString(R.string.registery_sucessfull), false)
                true
            }
        }
    }
    // END
}