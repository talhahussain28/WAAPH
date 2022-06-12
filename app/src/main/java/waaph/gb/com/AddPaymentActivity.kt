package waaph.gb.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_add_payment.*
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.Utils

class AddPaymentActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment)

        setOnClickListener()
        initialize()
    }

    override fun linkXML() {

    }

    override fun setOnClickListener() {
        next.setOnClickListener(this)
    }

    override fun initialize() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {
                addPayment()
            }
        }
    }

    private fun addPayment(){
        Utils.etValidate(edtPersonName)

        if (edtPersonName.text.toString().isNotEmpty()){
            Toast.makeText(this, "task done", Toast.LENGTH_SHORT).show()
        }
    }


}