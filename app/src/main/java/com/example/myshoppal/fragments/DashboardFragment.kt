package com.example.myshoppal.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myshoppal.R
import com.example.myshoppal.activities.SettingsActivity

class DashboardFragment : Fragment() {

    // TODO Step 5: Override the onCreate function and add the piece of code to inflate the option menu in fragment.
    // START
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // If we want to use the option menu in fragment we need to add it.
        setHasOptionsMenu(true)
    }
    // END

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        textView.text = "This is dashboard Fragment"
        return root
    }

    // TODO Step 6: Override the onCreateOptionMenu function and inflate the Dashboard menu file init.
    // START
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    // END

    // TODO Step 7: Override the onOptionItemSelected function and handle the action items init.
    // START
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {

            R.id.action_settings -> {

                // TODO Step 9: Launch the SettingActivity on click of action item.
                // START
                startActivity(Intent(activity, SettingsActivity::class.java))
                // END
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // END
}