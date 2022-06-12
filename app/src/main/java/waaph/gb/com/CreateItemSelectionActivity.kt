package waaph.gb.com

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_create_item_selection.*
import kotlinx.android.synthetic.main.custom_dialog.*
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.GeneralBottomAdapter
import waaph.gb.com.utils.Utils

class CreateItemSelectionActivity : BaseActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_item_selection)

        setOnClickListener()
    }

    override fun linkXML() {

    }

    override fun setOnClickListener() {
        btnCreateItem.setOnClickListener(this)
        showContactDetailList.setOnClickListener(this)
        salesTakerList.setOnClickListener(this)
        termsOfPaymentList.setOnClickListener(this)
        tvMethodOfPaymentList.setOnClickListener(this)
    }

    override fun initialize() {
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnCreateItem -> {
                addItem()
            }
            R.id.showContactDetailList -> {
                locationTypeDialog()
            }
            R.id.salesTakerList -> {
                salesTakerDialog()
            }
            R.id.termsOfPaymentList -> {
                termsOfPaymentDialog()
            }
            R.id.tvMethodOfPaymentList -> {
                methodOfPaymentDialog()
            }
        }
    }

    private fun locationTypeDialog() {

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
                        tvShowContactDetail.text = "Option 1"
                    }
                    1 -> {
                        tvShowContactDetail.text = "Option 2"
                    }
                    2 -> {
                        tvShowContactDetail.text = "Option 3"
                    }
                    3 -> {
                        tvShowContactDetail.text = "Option 4"
                    }
                    4 -> {
                        tvShowContactDetail.text = "Option 5"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun salesTakerDialog() {

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
                        tvShowSalesTaker.text = "Option 1"
                    }
                    1 -> {
                        tvShowSalesTaker.text = "Option 2"
                    }
                    2 -> {
                        tvShowSalesTaker.text = "Option 3"
                    }
                    3 -> {
                        tvShowSalesTaker.text = "Option 4"
                    }
                    4 -> {
                        tvShowSalesTaker.text = "Option 5"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun termsOfPaymentDialog() {

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
                        tvShowTermsOfPayment.text = "Option 1"
                    }
                    1 -> {
                        tvShowTermsOfPayment.text = "Option 2"
                    }
                    2 -> {
                        tvShowTermsOfPayment.text = "Option 3"
                    }
                    3 -> {
                        tvShowTermsOfPayment.text = "Option 4"
                    }
                    4 -> {
                        tvShowTermsOfPayment.text = "Option 5"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun methodOfPaymentDialog() {

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
                        tvShowMethodOfPayment.text = "Option 1"
                    }
                    1 -> {
                        tvShowMethodOfPayment.text = "Option 2"
                    }
                    2 -> {
                        tvShowMethodOfPayment.text = "Option 3"
                    }
                    3 -> {
                        tvShowMethodOfPayment.text = "Option 4"
                    }
                    4 -> {
                        tvShowMethodOfPayment.text = "Option 5"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun addItem(){
        Utils.etValidate(edtItemName)
        Utils.etValidate(edtItemCode)
        Utils.etValidate(edtSaleUnit)
        Utils.etValidate(edtRateAndUnitPrice)
        Utils.etValidate(edtQuality)
        Utils.etValidate(edtDiscount)
        Utils.etValidate(edtSchemeCode)
        Utils.etValidate(edtFOC)
        Utils.etValidate(edtSalesTaxAmount)
        Utils.etValidate(edtTotalAmount)
        Utils.etValidate(edtModeOfPayment)
        Utils.etValidate(edtOrderPriority)
        Utils.etValidate(edtDeliverDateAndTime)
        Utils.etValidate(edtDeliveryAddress)

        if (edtItemName.text!!.isNotEmpty()&&
            edtItemCode.text!!.isNotEmpty()&&
            edtSaleUnit.text!!.isNotEmpty()&&
            edtRateAndUnitPrice.text!!.isNotEmpty()&&
            edtQuality.text!!.isNotEmpty()&&
            edtDiscount.text!!.isNotEmpty()&&
            edtSchemeCode.text!!.isNotEmpty()&&
            edtFOC.text!!.isNotEmpty()&&
            edtSalesTaxAmount.text!!.isNotEmpty()&&
            edtModeOfPayment.text!!.isNotEmpty()&&
            edtOrderPriority.text!!.isNotEmpty()&&
            edtDeliverDateAndTime.text!!.isNotEmpty()&&
            edtDeliveryAddress.text!!.isNotEmpty()&&
            edtTotalAmount.text!!.isNotEmpty()
        ) {
            Toast.makeText(this, "task done", Toast.LENGTH_SHORT).show()
        }
    }



}