package waaph.gb.com.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_general.*
import waaph.gb.com.CreateGeneralDataActivity
import waaph.gb.com.R
import waaph.gb.com.adapters.GeneralAdapter
import waaph.gb.com.model.CreateGeneralModel

class GeneralFragment : Fragment(), View.OnClickListener {

    private lateinit var adapter : GeneralAdapter
    private lateinit var list: ArrayList<CreateGeneralModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return   inflater.inflate(R.layout.fragment_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        list = ArrayList()
        adapter = GeneralAdapter(requireContext(),list)
        recyclerViewGeneral.layoutManager = linearLayoutManager
        recyclerViewGeneral.adapter = adapter

        clickListners()
        GeneralData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> {
                val intent = Intent(requireActivity(), CreateGeneralDataActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun clickListners(){
        fab.setOnClickListener(this)

    }

    private fun GeneralData(){
        list.add(CreateGeneralModel("Miletap"))
        list.add(CreateGeneralModel("LuteBox"))
        list.add(CreateGeneralModel("Pnc solution"))
        list.add(CreateGeneralModel("Ibex"))
        list.add(CreateGeneralModel("rajby"))

    }

}