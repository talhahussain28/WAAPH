package waaph.gb.com.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_create_address_data.*
import kotlinx.android.synthetic.main.fragment_bank.*
import kotlinx.android.synthetic.main.fragment_contact.*
import waaph.gb.com.CreateContactActivity
import waaph.gb.com.CustomerDataFormActivity
import waaph.gb.com.R
import waaph.gb.com.adapters.ContactAdapter
import waaph.gb.com.model.CreateContactModel

class ContactFragment : Fragment(),View.OnClickListener {

   private lateinit var list : ArrayList<CreateContactModel>
   private lateinit var recyclerView: RecyclerView
   private lateinit var adapter : ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = ArrayList()
        recyclerView = contactRecyclerView
        adapter = ContactAdapter(requireContext(),list)

        setOnClickListeners()
        setUpRecyclerViewData(recyclerView,adapter)
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

    private fun setUpRecyclerViewData(recyclerView: RecyclerView, adapter: ContactAdapter){
        list.add(CreateContactModel("waleed","018236153421536172736"))
        list.add(CreateContactModel("tlaha","018236153421536172736"))
        list.add(CreateContactModel("hammad","018236153421536172736"))
        list.add(CreateContactModel("huzaifa","018236153421536172736"))
        list.add(CreateContactModel("anus ali","018236153421536172736"))
        list.add(CreateContactModel("hameed","018236153421536172736"))
        list.add(CreateContactModel("uzair","018236153421536172736"))
        list.add(CreateContactModel("saud","018236153421536172736"))
        list.add(CreateContactModel("rohit","018236153421536172736"))
        list.add(CreateContactModel("bilal","018236153421536172736"))
        list.add(CreateContactModel("majSHdyg","018236153421536172736"))
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

}