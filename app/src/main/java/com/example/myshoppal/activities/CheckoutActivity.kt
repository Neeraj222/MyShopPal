package com.example.myshoppal.activities

import android.os.Bundle
import com.example.myshoppal.R
import com.example.myshoppal.firestore.FirestoreClass
import com.example.myshoppal.models.Address
import com.example.myshoppal.models.Cart
import com.example.myshoppal.models.Product
import com.example.myshoppal.utils.Constants
import kotlinx.android.synthetic.main.activity_cart_list.*
import kotlinx.android.synthetic.main.activity_checkout.*


class CheckoutActivity : BaseActivity() {


    private var mAddressDetails: Address? = null
    private lateinit var mProductsList: ArrayList<Product>
    private lateinit var mCartItemsList: ArrayList<Cart>

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

        getProductList()

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

    private fun getProductList() {

        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getAllProductsList(this@CheckoutActivity)
    }


    fun successProductsListFromFireStore(productsList: ArrayList<Product>) {

        mProductsList = productsList
        getCartItemsList()

    }

    private fun getCartItemsList() {

        FirestoreClass().getCartList(this@CheckoutActivity)
    }


    fun successCartItemsList(cartList: ArrayList<Cart>) {

        hideProgressDialog()
        mCartItemsList = cartList

    }

}