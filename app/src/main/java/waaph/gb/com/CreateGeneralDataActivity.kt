package waaph.gb.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_create_general_data.*

class CreateGeneralDataActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_general_data)

        setUpClickListeners()
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
            Toast.makeText(this, "task done", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpClickListeners(){
        btnCreateGeneral.setOnClickListener(this)

    }

}