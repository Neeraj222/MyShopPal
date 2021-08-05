package com.example.myshoppal.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshoppal.R
import com.example.myshoppal.activities.AddProductActivity
import com.example.myshoppal.activities.Product
import com.example.myshoppal.activities.SettingsActivity
import com.example.myshoppal.firestore.FirestoreClass
import kotlinx.android.synthetic.main.fragment_product.*

class ProductsFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // If we want to use the option menu in fragment we need to add it.
        setHasOptionsMenu(true)
    }
    fun successProductsListFromFireStore(productsList: ArrayList<Product>) {
        for(i in productsList){
            Log.i("Product Name", i.title)
        }

    }

    private fun getProductListFromFireStore() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of Firestore class.
        FirestoreClass().getProductsList(this@ProductsFragment)
        hideProgressDialog()
    }

    override fun onResume() {
        super.onResume()
        getProductListFromFireStore()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)*/

        val root = inflater.inflate(R.layout.fragment_product, container, false)
//

        /*homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        return root
    }
    // END
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_product_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    // END

    // TODO Step 7: Override the onOptionItemSelected function and handle the action items init.
    // START
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {

            R.id.action_add_product -> {

                // TODO Step 9: Launch the SettingActivity on click of action item.
                // START
                startActivity(Intent(activity, AddProductActivity::class.java))
                // END
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // END
}