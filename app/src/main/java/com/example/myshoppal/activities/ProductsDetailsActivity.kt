package com.example.myshoppal.activities


import android.os.Bundle
import android.util.Log
import com.example.myshoppal.R
import com.example.myshoppal.firestore.FirestoreClass
import com.example.myshoppal.utils.Constants
import com.example.myshoppal.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_products_details.*

class ProductsDetailsActivity : BaseActivity() {

    private var mProductId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_details)
        setupActionBar()

        if(intent.hasExtra(Constants.EXTRA_PRODUCT_ID)){
            mProductId = intent.getStringExtra(Constants.EXTRA_PRODUCT_ID)!!
            Log.i("Product id", mProductId)
        }
        getProductDetails()
    }
    fun productDetailsSuccess(product: Product) {

        hideProgressDialog()

        GlideLoader(this@ProductsDetailsActivity).loadProductPicture(
            product.image,
            iv_product_detail_image
        )

        tv_product_details_title.text = product.title
        tv_product_details_price.text = "$${product.price}"
        tv_product_details_description.text = product.description
        tv_product_details_available_quantity.text = product.stock_quantity
    }

    private fun getProductDetails() {

        // Show the product dialog
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of FirestoreClass to get the product details.
        FirestoreClass().getProductDetails(this@ProductsDetailsActivity, mProductId)
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_product_details_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }
        toolbar_product_details_activity.setNavigationOnClickListener { onBackPressed() }
    }
}