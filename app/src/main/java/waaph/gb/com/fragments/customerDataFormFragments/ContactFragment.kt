package waaph.gb.com.fragments.customerDataFormFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_contact.*
import waaph.gb.com.*
import waaph.gb.com.activities.ContactDetailActivity
import waaph.gb.com.activities.CreateContactActivity
import waaph.gb.com.adapters.ContactAdapter
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.model.CreateContactModel
import waaph.gb.com.model.Data

class ContactFragment : Fragment(), View.OnClickListener, OnRecyclerViewItemClickListener<Data> {

    private lateinit var list: ArrayList<CreateContactModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactAdapter

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
        adapter = ContactAdapter(requireContext(), list, this)

        setOnClickListeners()
        setUpRecyclerViewData(recyclerView, adapter)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.routeToCreateAddress -> {
                val intent = Intent(requireContext(), CreateContactActivity::class.java)
                startActivity(intent)
                //(activity as CustomerDataFormActivity).setCurrentItem(3)
            }
        }
    }

    private fun setOnClickListeners() {
        routeToCreateAddress.setOnClickListener(this)
    }

    private fun setUpRecyclerViewData(recyclerView: RecyclerView, adapter: ContactAdapter) {
        list.add(CreateContactModel("John", "018236153421536172736"))
        list.add(CreateContactModel("Mark", "018236153421536172736"))
        list.add(CreateContactModel("Ali", "018236153421536172736"))
        list.add(CreateContactModel("Pelo", "018236153421536172736"))
        list.add(CreateContactModel("Cris", "018236153421536172736"))
        list.add(CreateContactModel("Haris", "018236153421536172736"))
        list.add(CreateContactModel("Martix", "018236153421536172736"))
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    override fun onItemClick(itemView: View?, position: Int) {
        val intent = Intent(requireContext(), ContactDetailActivity::class.java)
        startActivity(intent)
    }

}