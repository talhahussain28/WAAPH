package waaph.gb.com

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_create_bank.*
import kotlinx.android.synthetic.main.activity_create_general_data.*
import kotlinx.android.synthetic.main.custom_dialog.*
import waaph.gb.com.utils.GeneralBottomAdapter

class CreateBankActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_bank)

        setOnClickListeners()

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bank -> {
                pickerActionDialog()
            }
            R.id.next ->{
                createBank()
            }
        }
    }

     private fun pickerActionDialog() {

         val dialog = Dialog(this)
         dialog.setContentView(R.layout.custom_dialog)
         dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation_2
         dialog.window!!.setGravity(Gravity.BOTTOM)
         dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
         dialog.window!!.setLayout(
             WindowManager.LayoutParams.MATCH_PARENT,
             WindowManager.LayoutParams.WRAP_CONTENT
         )

         dialog.setCancelable(true)
         dialog.setCanceledOnTouchOutside(true)

         val listView = dialog.findViewById<ListView>(R.id.listView)
         val list: MutableList<GeneralBottomAdapter.ListItemModel> = ArrayList()

         list.add(GeneralBottomAdapter.ListItemModel("Meezan Bank"))
         list.add(GeneralBottomAdapter.ListItemModel("Option 2"))
         list.add(GeneralBottomAdapter.ListItemModel("Option 3"))
         list.add(GeneralBottomAdapter.ListItemModel("Option 4"))

         val adapter = GeneralBottomAdapter(this, "", list)
         listView.adapter = adapter
         dialog.listView.onItemClickListener =
             AdapterView.OnItemClickListener { parent, view, position, id ->
                 val data = parent.getItemAtPosition(position)
                         as GeneralBottomAdapter.ListItemModel

                 when (position) {
                     0 -> {
                         tvBank.text = "Meezan Bank"
                     }
                     1 -> {
                         tvBank.text = "UBL Bank"
                     }
                     2 -> {
                         tvBank.text = "HBL Bank"
                     }
                     3 -> {
                         tvBank.text = "Alied Bank"
                     }
                 }
                 dialog.dismiss()

             }
         dialog.show()

     }

    private fun createBank(){
        etValidate(editText_Name)
        etValidate(edtAccountNo)
        etValidate(editText_budget)

        if (editText_Name.text!!.isNotEmpty()&&
            editText_budget.text!!.isNotEmpty()&&
            edtAccountNo.text!!.isNotEmpty()
        ){
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
        bank.setOnClickListener(this)
    }
}