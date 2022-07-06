package waaph.gb.com.fragments.customerDataFormFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_contact.*
import waaph.gb.com.R
import waaph.gb.com.activities.ContactDetailActivity
import waaph.gb.com.activities.CreateContactActivity
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.adapters.ContactAdapter
import waaph.gb.com.entities.cdf.ContactEnt
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.utils.*

class ContactFragment : BaseFragment(), View.OnClickListener, OnRecyclerViewItemClickListener<ContactEnt> {

    companion object{
        const val CREATE_CONTACT_CODE = 1002
    }

    private var list =  ArrayList<ContactEnt>()
    private lateinit var adapter: ContactAdapter
    private var contactData: ContactEnt? = null

    private var prefs: SaveInSharedPreference? = null
    private var gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = SaveInSharedPreference(requireContext())

        fab.setOnClickListener(this)
        btnNext.setOnClickListener(this)

        setUpRecyclerViewData()

        if (!prefs!!.getString(Constants.ARG_CONTACT).isNullOrEmpty()){
            btnNext.show()
        }else{
            btnNext.gone()
        }
    }

    override fun linkXML(view: View?) {

    }

    override fun setOnClickListener() {

    }

    override fun initialize() {

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> {
                val intent = Intent(requireActivity(), CreateContactActivity::class.java)
                startActivityForResult(intent, CREATE_CONTACT_CODE)
            }
            R.id.btnNext -> {
                (activity as CustomerDataFormActivity).setCurrentItem(3)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                CREATE_CONTACT_CODE -> {
                    contactData = gson.fromJson(data!!.extras!!.getString("data"),
                        ContactEnt::class.java)

                    list.add(contactData!!)

                    // Update and get new list from prefs
                    prefs!!.setString(Constants.ARG_CONTACT, gson.toJson(list))
                    adapter.updateList(getNewList())

                    btnNext.show()
                }
            }
        }
    }

    private fun getNewList(): ArrayList<ContactEnt> {
        val type = object : TypeToken<ArrayList<ContactEnt>>() {}.type!!
        return gson.fromJson(prefs!!.getString(Constants.ARG_CONTACT), type)
    }

    private fun setUpRecyclerViewData() {
        adapter = ContactAdapter(requireContext(), ArrayList(), this)
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        contactRecyclerView.layoutManager = linearLayoutManager
        contactRecyclerView.adapter = adapter
    }

    override fun onItemClick(itemView: View?, position: Int) {
        val intent = Intent(requireContext(), ContactDetailActivity::class.java)
        startActivity(intent)
    }

}