package waaph.gb.com.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_general_data.*
import waaph.gb.com.R
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.Utils

class CreateGeneralDataActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_general_data)

        setOnClickListener()
    }

    override fun linkXML() {

    }

    override fun setOnClickListener() {
        btnCreateGeneral.setOnClickListener(this)
    }

    override fun initialize() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreateGeneral -> {
                createGeneral()
            }
        }
    }


    private fun createGeneral(){
        Utils.etValidate(edtBussinessName)
        Utils.etValidate(edtCustomerGroup)
        Utils.etValidate(edtBussinessType)
        Utils.etValidate(edtCNIC)
        Utils.etValidate(edtNTN)
        Utils.etValidate(edtRegion)
        Utils.etValidate(edtFAX)
        Utils.etValidate(edtMobile)
        Utils.etValidate(edtWhatsApp)
        Utils.etValidate(edtWebSite)
        Utils.etValidate(edtEmail)
        Utils.etValidate(edtOrganizationName)
        Utils.etValidate(edtPhone)

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
            Toast.makeText(this, "task done", Toast.LENGTH_SHORT).show()
        }
    }

}