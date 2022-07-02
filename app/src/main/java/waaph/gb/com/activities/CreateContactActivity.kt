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
import kotlinx.android.synthetic.main.activity_create_contact.*
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_general.*
import waaph.gb.com.R
import waaph.gb.com.entities.cdf.ContactEnt
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.GeneralBottomAdapter
import waaph.gb.com.utils.Utils
import waaph.gb.com.utils.getTextToString

class CreateContactActivity : BaseActivity(), View.OnClickListener {

    private var contactEnt: ContactEnt? = null

    private var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contact)

        initialize()
        setOnClickListener()
    }

    override fun linkXML() {
    }

    override fun setOnClickListener() {
        btnNext.setOnClickListener(this)
        openCategoryList.setOnClickListener(this)
    }

    override fun initialize() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNext -> {
                addContact()
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

        list.add(GeneralBottomAdapter.ListItemModel("Option 1"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 2"))
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
                        openCategoryList.text = "Option 1"
                    }
                    1 -> {
                        openCategoryList.text = "Option 2"
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

    private fun addContact() {
        Utils.etValidate(edtPersonName)
        Utils.etValidate(edtDesignation)
        Utils.etValidate(edtPhoneNumber)
        Utils.etValidate(edtEmailContact)

        if (edtPersonName.text!!.isNotEmpty() &&
            edtDesignation.text!!.isNotEmpty() &&
            edtPhoneNumber.text!!.isNotEmpty() &&
            edtEmailContact.text!!.isNotEmpty()
        ) {
            contactEnt = ContactEnt(
                0,
                0,
                0,
                edtPersonName.getTextToString(),
                edtDesignation.getTextToString(),
                "",
                edtPhoneNumber.getTextToString(),
                edtEmailContact.getTextToString(),
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

            hideKeyBoard(edtEmailContact)

            var intent = Intent()
            intent.putExtra("data", gson.toJson(contactEnt))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}