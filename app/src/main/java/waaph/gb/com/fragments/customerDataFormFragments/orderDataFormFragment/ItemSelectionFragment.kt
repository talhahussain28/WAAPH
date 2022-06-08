package waaph.gb.com.fragments.customerDataFormFragments.orderDataFormFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_item_selection.*
import waaph.gb.com.ItemSelectedDetailActivity
import waaph.gb.com.R
import waaph.gb.com.adapters.SelectItemAdapter
import waaph.gb.com.CreateItemSelectionActivity
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.model.SelectItemModel

class ItemSelectionFragment : Fragment() , View.OnClickListener,
    OnRecyclerViewItemClickListener<SelectItemModel> {

    private lateinit var list: ArrayList<SelectItemModel>
    private lateinit var adapter: SelectItemAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = ArrayList()
        adapter = SelectItemAdapter(requireContext(), list, this)
        recyclerView = recyclerViewItemSelection

        fabSelectItem.setOnClickListener(this)
        setUpRecyclerViewData(recyclerView, adapter)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fabSelectItem -> {
                startActivity(Intent(requireContext(), CreateItemSelectionActivity::class.java))
            }

        }
    }

    override fun onItemClick(itemView: View?, position: Int) {
        val intent = Intent(requireContext(), ItemSelectedDetailActivity::class.java)
        startActivity(intent)
    }

    private fun setUpRecyclerViewData(recyclerView: RecyclerView, adapter: SelectItemAdapter) {
        list.add(SelectItemModel("waleed"))
        list.add(SelectItemModel("tlaha"))
        list.add(SelectItemModel("hammad"))
        list.add(SelectItemModel("huzaifa"))
        list.add(SelectItemModel("anus ali"))
        list.add(SelectItemModel("hameed"))
        list.add(SelectItemModel("uzair"))
        list.add(SelectItemModel("saud"))
        list.add(SelectItemModel("rohit"))
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

}