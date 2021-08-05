package com.example.myshoppal.activities
import android.content.Intent
import android.os.Bundle

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.example.myshoppal.R
import com.example.myshoppal.firestore.FirestoreClass
import com.example.myshoppal.models.User
import com.example.myshoppal.utils.Constants
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_login)

        // This is used to hide the status bar and make the login screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val tvRegister = findViewById<TextView>(R.id.tv_register)
        tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        val btnLogin = findViewById<TextView>(R.id.btn_login)
        btnLogin.setOnClickListener(this)

        val tvForgotPassword = findViewById<TextView>(R.id.tv_forgot_password)
        tvForgotPassword.setOnClickListener{
            val intent = Intent(this@LoginActivity, ForgotPassword::class.java)
            startActivity(intent)
        }

    }


    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {

                R.id.tv_forgot_password -> {
                    val intent = Intent(this@LoginActivity, ForgotPassword::class.java)
                    startActivity(intent)

                }

                R.id.btn_login -> {

                    // START
                   loginRegisteredUser()
                    // END
                }

                R.id.tv_register -> {
                    // Launch the register screen when the user clicks on the text.
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun validateLoginDetails(): Boolean {
        val email = findViewById<EditText>(R.id.et_email)
        val password = findViewById<EditText>(R.id.et_password)
        return when {
            TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {

                true
            }
        }
    }
    private fun loginRegisteredUser(){
        if (validateLoginDetails()){
            showProgressDialog(resources.getString(R.string.please_wait))
            val email : String = findViewById<TextView>(R.id.et_email).text.toString()
            val password : String = findViewById<TextView>(R.id.et_password).text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if(task.isSuccessful){

                        FirestoreClass().getUserDetails(this@LoginActivity)
                    }else{
                        hideProgressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                    }
                }
        }
    }
    fun userLoggedInSuccess(user: User) {

        // Hide the progress dialog

        hideProgressDialog()
        if (user.profileCompleted == 0) {
            // If the user profile is incomplete then launch the UserProfileActivity.
            val intent = Intent(this@LoginActivity, UserProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_DETAILS,user)
            startActivity(intent)
        } else {
            // Redirect the user to Main Screen after log in.
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
        finish()
        // END
    }
}






