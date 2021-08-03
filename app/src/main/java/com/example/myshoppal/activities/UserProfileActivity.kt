package com.example.myshoppal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myshoppal.R
import com.example.myshoppal.models.User
import com.example.myshoppal.utils.Constants

import kotlinx.android.synthetic.main.activity_user_profile.*

/**
 * A user profile screen.
 */
class UserProfileActivity : AppCompatActivity() {

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_user_profile)


        // TODO Step 5: Retrieve the User details from intent extra.
        // START
        // Create a instance of the User model class.
        var userDetails: User = User()
        if(intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            // Get the user details from intent as a ParcelableExtra.
            userDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }
        // END

        // TODO Step 6: After receiving the user details from intent set it to the UI.
        // START
        // Here, the some of the edittext components are disabled because it is added at a time of Registration.
        et_first_name.isEnabled = false
        et_first_name.setText(userDetails.firstName)

        et_last_name.isEnabled = false
        et_last_name.setText(userDetails.lastName)

        et_email.isEnabled = false
        et_email.setText(userDetails.email)
        // END
    }
}