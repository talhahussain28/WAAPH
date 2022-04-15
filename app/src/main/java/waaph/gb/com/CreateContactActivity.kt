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
import kotlinx.android.synthetic.main.activity_create_contact.*
import kotlinx.android.synthetic.main.activity_create_general_data.*
import kotlinx.android.synthetic.main.custom_dialog.*
import waaph.gb.com.utils.GeneralBottomAdapter

class CreateContactActivity : AppCompatActivity(), View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contact)

        setOnClickListener()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNext -> {
                addAddress()
            }
            R.id.openCategoryList -> {
                pickerActionDialog()
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

        list.add(GeneralBottomAdapter.ListItemModel("waleed"))
        list.add(GeneralBottomAdapter.ListItemModel("anus ali"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 3"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 4"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 5"))

        val adapter = GeneralBottomAdapter(this, "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        openCategoryList.text = "waleed"
                    }
                    1 -> {
                        openCategoryList.text = "anus ali"
                    }
                    2 -> {
                        openCategoryList.text = "Option 3"
                    }
                    3 -> {
                        openCategoryList.text = "Option 4"
                    }
                    4 -> {
                        openCategoryList.text = "Option 5"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun addAddress(){
        etValidate(edtPersonName)
        etValidate(edtDesignation)
        etValidate(edtPhoneNumber)
        etValidate(edtEmailContact)

        if (edtPersonName.text!!.isNotEmpty()&&
            edtDesignation.text!!.isNotEmpty()&&
            edtPhoneNumber.text!!.isNotEmpty()&&
            edtEmailContact.text!!.isNotEmpty()
        ) {
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

    private fun setOnClickListener(){
        btnNext.setOnClickListener(this)
        openCategoryList.setOnClickListener(this)
    }
}