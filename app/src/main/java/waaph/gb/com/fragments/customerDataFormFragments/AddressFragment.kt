package waaph.gb.com.fragments.customerDataFormFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_address.*
import waaph.gb.com.AddressDetailActivity
import waaph.gb.com.CreateAddressDataActivity
import waaph.gb.com.R
import waaph.gb.com.adapters.AddressAdapter
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.model.CreateAddressModel
import waaph.gb.com.model.Data

class AddressFragment : Fragment(), View.OnClickListener, OnRecyclerViewItemClickListener<Data> {

    private lateinit var list: ArrayList<CreateAddressModel>
    private lateinit var adapter: AddressAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> {
                val intent = Intent(requireActivity(), CreateAddressDataActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = ArrayList()
        adapter = AddressAdapter(requireContext(), list, this)
        recyclerView = recyclerViewAddress

        fab.setOnClickListener(this)
        setUpRecyclerViewData(recyclerView, adapter)
    }

    private fun setUpRecyclerViewData(recyclerView: RecyclerView, adapter: AddressAdapter) {
        list.add(CreateAddressModel("waleed"))
        list.add(CreateAddressModel("tlaha"))
        list.add(CreateAddressModel("hammad"))
        list.add(CreateAddressModel("huzaifa"))
        list.add(CreateAddressModel("anus ali"))
        list.add(CreateAddressModel("hameed"))
        list.add(CreateAddressModel("uzair"))
        list.add(CreateAddressModel("saud"))
        list.add(CreateAddressModel("rohit"))
        list.add(CreateAddressModel("bilal"))
        list.add(CreateAddressModel("majSHdyg"))
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