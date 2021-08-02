package com.example.myshoppal.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.TextView
import com.example.myshoppal.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


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
            registerUser()
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
//        setSupportActionBar(findViewById(R.id.toolbar_register_activity))
//
//        val actionBar = supportActionBar
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true)
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
//        }
//        val toolbar = findViewById<TextView>().setNavigationOnClickListener { onBackPressed() }
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
    private fun registerUser() {

        // Check with validate function if the entries are valid or not.
        if (validateRegisterDetails()) {
            showProgressDialog(resources.getString(R.string.please_wait))

            val email : String = findViewById<TextView>(R.id.et_email).text.toString()
            val password : String = findViewById<TextView>(R.id.et_password).text.toString()

            // Create an instance and create a register a user with email and password.
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        hideProgressDialog()

                        // If the registration is successfully done
                        if (task.isSuccessful) {

                            // Firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            showErrorSnackBar(
                                "You are registered successfully. Your user id is ${firebaseUser.uid}",
                                false
                            )
                            FirebaseAuth.getInstance().signOut()
                            finish()
                        } else {
                            // If the registering is not successful then show error message.
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    })
        }
    }
}