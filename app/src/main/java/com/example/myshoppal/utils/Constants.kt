package com.example.myshoppal.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {

    const val USERS: String = "users"
    const val MYSHOP_PREFERECNES: String = "MyShopPalPrefs"
    const val LOGGED_IN_USER: String = "logged_in_username"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMG_REQUEST_CODE = 2

    const val FIRST_NAME:  String = "firstName"
    const val LAST_NAME: String = "lastName"

    const val MALE:  String = "male"
    const val FEMALE: String = "female"

    const val MOBILE:  String = "mobile"
    const val GENDER: String = "gender"
    const val IMAGE: String = "image"
    const val COMPLETE_PROFILE: String = "profileCompleted"

    const val USER_PROFILE_IMAGE: String = "User_Profile_Image"

    const val PRODUCT_IMAGE: String = "Product_Image"
    const val PRODUCTS: String = "products"
    const val USER_ID: String = "user_id"

    const val EXTRA_PRODUCT_ID: String = "extra_product_id"




    fun showImageChooser(activity: Activity) {
        // An intent for launching the image selection of phone storage.
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Launches the image selection of phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMG_REQUEST_CODE)
    }

    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        /*
         * MimeTypeMap: Two-way map that maps MIME-types to file extensions and vice versa.
         *
         * getSingleton(): Get the singleton instance of MimeTypeMap.
         *
         * getExtensionFromMimeType: Return the registered extension for the given MIME type.
         *
         * contentResolver.getType: Return the MIME type of the given content URL.
         */
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }

}