package waaph.gb.com.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_create_general_data.*
import kotlinx.android.synthetic.main.fragment_general.*
import kotlinx.android.synthetic.main.fragment_general.btnCreateGeneral
import kotlinx.android.synthetic.main.fragment_general.edtBussinessName
import kotlinx.android.synthetic.main.fragment_general.edtBussinessType
import kotlinx.android.synthetic.main.fragment_general.edtCNIC
import kotlinx.android.synthetic.main.fragment_general.edtCustomerGroup
import kotlinx.android.synthetic.main.fragment_general.edtEmail
import kotlinx.android.synthetic.main.fragment_general.edtFAX
import kotlinx.android.synthetic.main.fragment_general.edtMobile
import kotlinx.android.synthetic.main.fragment_general.edtNTN
import kotlinx.android.synthetic.main.fragment_general.edtOrganizationName
import kotlinx.android.synthetic.main.fragment_general.edtPhone
import kotlinx.android.synthetic.main.fragment_general.edtRegion
import kotlinx.android.synthetic.main.fragment_general.edtWebSite
import kotlinx.android.synthetic.main.fragment_general.edtWhatsApp
import waaph.gb.com.CreateGeneralDataActivity
import waaph.gb.com.R
import waaph.gb.com.adapters.GeneralAdapter
import waaph.gb.com.model.CreateGeneralModel
import waaph.gb.com.utils.BaseFragment

class GeneralFragment : BaseFragment(), View.OnClickListener {

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

  /*      val linearLayoutManager = LinearLayoutManager(requireContext())
        list = ArrayList()
        adapter = GeneralAdapter(requireContext(),list)

        recyclerViewGeneral.layoutManager = linearLayoutManager
        recyclerViewGeneral.adapter = adapter*/
        setOnClickListener()
        initialize()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreateGeneral -> {
                createGeneral()
            }
        }
    }

    private fun etValidate(edittext: TextInputEditText): Boolean {
        var validate = edittext.text.toString()
        validate = validate.replace("\\s+".toRegex(), " ").trim { it <= ' ' }
        if (validate.isEmpty()) {
            edittext.error = "Required"
            return false
        }
        return true
    }

    private fun createGeneral(){
        etValidate(edtBussinessName)
        etValidate(edtCustomerGroup)
        etValidate(edtBussinessType)
        etValidate(edtCNIC)
        etValidate(edtNTN)
        etValidate(edtRegion)
        etValidate(edtFAX)
        etValidate(edtMobile)
        etValidate(edtWhatsApp)
        etValidate(edtWebSite)
        etValidate(edtEmail)
        etValidate(edtOrganizationName)
        etValidate(edtPhone)

        if (edtBussinessName.text!!.isNotEmpty()&&
            edtCustomerGroup.text!!.isNotEmpty()&&
            edtBussinessType.text!!.isNotEmpty()&&
            edtCNIC.text!!.isNotEmpty()&&
            edtNTN.text!!.isNotEmpty()&&
            edtRegion.text!!.isNotEmpty()&&
            edtFAX.text!!.isNotEmpty()&&
            edtMobile.text!!.isNotEmpty()&&
            edtWhatsApp.text!!.isNotEmpty()&&
            edtWebSite.text!!.isNotEmpty()&&
            edtEmail.text!!.isNotEmpty()&&
            edtOrganizationName.text!!.isNotEmpty()&&
            edtPhone.text!!.isNotEmpty()
        ) {
            Toast.makeText(requireContext(), "task done", Toast.LENGTH_SHORT).show()
        }
    }


    override fun linkXML(view: View?) {
    }

    override fun setOnClickListener() {
        btnCreateGeneral.setOnClickListener(this)
    }

    override fun initialize() {
/*        list.add(CreateGeneralModel("Miletap"))
        list.add(CreateGeneralModel("LuteBox"))
        list.add(CreateGeneralModel("Pnc solution"))
        list.add(CreateGeneralModel("Ibex"))
        list.add(CreateGeneralModel("rajby"))*/
    }



}