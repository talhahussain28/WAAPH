package waaph.gb.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_create_address_data.*
import kotlinx.android.synthetic.main.activity_create_general_data.*

class CreateAddressDataActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_address_data)

        setOnClickListeners()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAddAddress -> {
                addAddress()
                //(activity as CustomerDataFormActivity).setCurrentItem(3)
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

    private fun addAddress(){

        etValidate(edtAddressName)
        etValidate(edtAddressLine)
        etValidate(edtPostalCode)
        etValidate(edtWorkingHours)

        if (edtAddressName.text!!.isNotEmpty()&&
            edtAddressLine.text!!.isNotEmpty()&&
            edtPostalCode.text!!.isNotEmpty()&&
            edtWorkingHours.text!!.isNotEmpty()
        ) {
            Toast.makeText(this, "task done", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setOnClickListeners(){
        btnAddAddress.setOnClickListener(this)
    }



}