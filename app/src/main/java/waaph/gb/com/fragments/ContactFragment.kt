package waaph.gb.com.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_create_address_data.*
import kotlinx.android.synthetic.main.fragment_contact.*
import waaph.gb.com.CreateContactActivity
import waaph.gb.com.CustomerDataFormActivity
import waaph.gb.com.R

class ContactFragment : Fragment(),View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setOnClickListeners()
    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.routeToCreateAddress -> {
                val intent = Intent(requireContext(),CreateContactActivity::class.java)
                startActivity(intent)

                //(activity as CustomerDataFormActivity).setCurrentItem(3)

            }

        }
    }

    private fun setOnClickListeners(){
        routeToCreateAddress.setOnClickListener(this)
    }
}