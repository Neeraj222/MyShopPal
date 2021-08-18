package com.example.myshoppal.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myshoppal.R
import com.example.myshoppal.adapters.AddressListAdapter
import com.example.myshoppal.firestore.FirestoreClass
import com.example.myshoppal.models.Address
import kotlinx.android.synthetic.main.activity_address_list.*

class AddressListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)
        setupActionBar()
        getAddressList()

        tv_add_address.setOnClickListener{
            val intent = Intent(this@AddressListActivity, AddEditAddressActivity::class.java)
            startActivity(intent)
        }

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

    fun successAddressListFromFirestore(addressList: ArrayList<Address>) {
        // Hide the progress dialog
        hideProgressDialog()

        if(addressList.size > 0){
            rv_address_list.visibility = View.VISIBLE
            tv_no_address_found.visibility = View.GONE
            rv_address_list.layoutManager = LinearLayoutManager(this@AddressListActivity)
            rv_address_list.setHasFixedSize(true)

            val addressAdapter = AddressListAdapter(this, addressList)
            rv_address_list.adapter = addressAdapter
        }else{
            rv_address_list.visibility = View.GONE
            tv_no_address_found.visibility = View.VISIBLE
        }
    }

    private fun getAddressList() {

        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getAddressesList(this@AddressListActivity)
    }



}
