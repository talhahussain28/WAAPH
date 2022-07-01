package waaph.gb.com.fragments.customerDataFormFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_customer_data_form.*
import kotlinx.android.synthetic.main.fragment_address.*
import waaph.gb.com.activities.AddressDetailActivity
import waaph.gb.com.activities.CreateAddressDataActivity
import waaph.gb.com.R
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.adapters.AddressAdapter
import waaph.gb.com.entities.cdf.AddressEnt
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.model.Data
import waaph.gb.com.utils.Constants.Companion.ARG_ADDRESS
import waaph.gb.com.utils.SaveInSharedPreference

class AddressFragment : Fragment(), View.OnClickListener, OnRecyclerViewItemClickListener<Data> {

    companion object{
        const val CREATE_ADDRESS_CODE = 1001
    }

    private var list =  ArrayList<AddressEnt>()
    private lateinit var adapter: AddressAdapter
    private lateinit var recyclerView: RecyclerView
    private var addressData: AddressEnt? = null

    private var prefs: SaveInSharedPreference? = null
    private var gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_address, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = SaveInSharedPreference(requireContext())

        disableOtherTabsExcept(1)

        recyclerView = recyclerViewAddress

        fab.setOnClickListener(this)
        setUpRecyclerViewData()

        /*if (!prefs!!.getString(ARG_ADDRESS).isNullOrEmpty()){
            list = getNewList()
            adapter.updateList(getNewList())
        }*/
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> {
                val intent = Intent(requireActivity(), CreateAddressDataActivity::class.java)
                startActivityForResult(intent, CREATE_ADDRESS_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                CREATE_ADDRESS_CODE -> {
                    addressData = gson.fromJson(data!!.extras!!.getString("data"),
                        AddressEnt::class.java)

                    list.add(addressData!!)

                    // Update and get new list from prefs
                    prefs!!.setString(ARG_ADDRESS, gson.toJson(list))
                    adapter.updateList(getNewList())
                }
            }
        }
    }

    private fun disableOtherTabsExcept(currentTabIndex: Int) {
        for (i in 0..6 step currentTabIndex){
            (activity as CustomerDataFormActivity).tabLayout.getTabAt(i)?.view!!.isClickable = false
        }

    }

    private fun getNewList(): ArrayList<AddressEnt> {
        val type = object : TypeToken<ArrayList<AddressEnt>>() {}.type!!
        return gson.fromJson(prefs!!.getString(ARG_ADDRESS), type)
    }

    private fun setUpRecyclerViewData() {
        adapter = AddressAdapter(requireContext(), ArrayList(), this)
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    override fun onItemClick(itemView: View?, position: Int) {
        val intent = Intent(requireContext(), AddressDetailActivity::class.java)
        startActivity(intent)
    }

}