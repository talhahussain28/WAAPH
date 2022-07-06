package waaph.gb.com.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_create_bank.*
import kotlinx.android.synthetic.main.activity_create_contact.*
import kotlinx.android.synthetic.main.custom_dialog.*
import waaph.gb.com.R
import waaph.gb.com.entities.cdf.BankEnt
import waaph.gb.com.entities.cdf.ContactEnt
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.GeneralBottomAdapter
import waaph.gb.com.utils.Utils
import waaph.gb.com.utils.getTextToString

class CreateBankActivity : BaseActivity(), View.OnClickListener {

    private var bankEnt: BankEnt? = null

    private var gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_bank)

        initialize()
        setOnClickListener()
    }

    override fun linkXML() {

    }

    override fun setOnClickListener() {
        next.setOnClickListener(this)
        bank.setOnClickListener(this)
    }

    override fun initialize() {
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
        Utils.etValidate(editText_Name)
        Utils.etValidate(edtAccountNo)
        Utils.etValidate(editText_budget)

        if (editText_Name.text!!.isNotEmpty()&&
            editText_budget.text!!.isNotEmpty()&&
            edtAccountNo.text!!.isNotEmpty()
        ){
            var bankEnt = BankEnt(
                0,
                0,
                editText_Name.getTextToString(),
                edtAccountNo.getTextToString(),
                "",
                "",
                0,
                "",
                0,
                "",
                0,
                "",
                0,
                "",
                false,
                false
            )

            hideKeyBoard(edtAccountNo)

            var intent = Intent()
            intent.putExtra("data", gson.toJson(bankEnt))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}