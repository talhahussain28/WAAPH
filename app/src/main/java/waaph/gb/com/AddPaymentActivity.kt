package waaph.gb.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_add_payment.*

class AddPaymentActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment)

        setOnClickListeners()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {
                addPayment()
            }
        }
    }

    private fun addPayment(){
        etValidate(edtPersonName)

        if (edtPersonName.text.toString().isNotEmpty()){
            Toast.makeText(this, "task done", Toast.LENGTH_SHORT).show()
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

    private fun setOnClickListeners(){
        next.setOnClickListener(this)
    }

}