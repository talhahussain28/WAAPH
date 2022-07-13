package waaph.gb.com.fragments.customerDataFormFragments.orderDataFormFragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_general_odf.*
import waaph.gb.com.R
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.activities.OrderDataFormActivity
import waaph.gb.com.entities.odf.GeneralOdfEnt
import waaph.gb.com.utils.BaseFragment
import waaph.gb.com.utils.Constants.Companion.ARG_GENERAL_ODF
import waaph.gb.com.utils.GeneralBottomAdapter
import waaph.gb.com.utils.SaveInSharedPreference
import waaph.gb.com.utils.Utils.Companion.etValidate
import waaph.gb.com.utils.show

class GeneralFragmentODF : BaseFragment(), View.OnClickListener {
    private var prefs: SaveInSharedPreference? = null
    private var gson = Gson()
    private var generalOdfData : GeneralOdfEnt? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general_odf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = SaveInSharedPreference(requireContext())

        setOnClickListener()
        initialize()
    }

    override fun linkXML(view: View?) {
    }

    override fun setOnClickListener() {
        btnCreateGeneralODF.setOnClickListener(this)
        currencyTypeList.setOnClickListener(this)
        dealForBranchTypeList.setOnClickListener(this)
        orderStatusTypeList.setOnClickListener(this)
    }

    override fun initialize() {
        setTextWatchers()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreateGeneralODF -> {
               // createGeneral()
                createGeneralODF()
            }
            R.id.currencyTypeList -> {
                currencyDialog()
            }
            R.id.dealForBranchTypeList -> {
                dealForBranchDialog()
            }
            R.id.orderStatusTypeList -> {
                orderStatusDialog()
            }

        }
    }
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {

            // TODO: Temp Code
            btnCreateGeneralODF.show()
            //setCreateButtonStatus()

//            validateEditTexts()
        }
    }

    private fun setTextWatchers() {
        edtOrderBookerName.addTextChangedListener(textWatcher)
        /*edtCNIC.addTextChangedListener(textWatcher)
        edtNTN.addTextChangedListener(textWatcher)
        edtPhone.addTextChangedListener(textWatcher)
        edtFAX.addTextChangedListener(textWatcher)
        edtMobile.addTextChangedListener(textWatcher)
        edtWhatsApp.addTextChangedListener(textWatcher)
        edtWebSite.addTextChangedListener(textWatcher)
        edtEmail.addTextChangedListener(textWatcher)
        edtOrganizationName.addTextChangedListener(textWatcher)*/
    }

    private fun createGeneral() {
        etValidate(edtOrderBookerName)
        etValidate(edtCustomerName)
        etValidate(edtCustomerCode)
        etValidate(edtBusinessAndBillingAddress)
        etValidate(edtOriginMode)
        etValidate(edtRemarks)
        etValidate(edtOrderAmount)
        etValidate(edtOrderId)

        if (edtOrderBookerName.text!!.isNotEmpty() &&
            edtCustomerName.text!!.isNotEmpty() &&
            edtCustomerCode.text!!.isNotEmpty() &&
            edtOriginMode.text!!.isNotEmpty() &&
            edtRemarks.text!!.isNotEmpty() &&
            edtOrderAmount.text!!.isNotEmpty() &&
            edtOrderId.text!!.isNotEmpty() &&
            edtBusinessAndBillingAddress.text!!.isNotEmpty()
        ) {

            Toast.makeText(requireContext(), "task Done", Toast.LENGTH_SHORT).show()
        }


    }

    private fun createGeneralODF(){
        generalOdfData = GeneralOdfEnt(
            0,edtOrderBookerName.text.toString(),
            edtCustomerName.text.toString(),
            edtCustomerCode.text.toString(),
            edtBusinessAndBillingAddress.text.toString(),
            "PKR",edtOriginMode.text.toString(),
            "Defence",edtRemarks.text.toString(),0)

        prefs?.setString(ARG_GENERAL_ODF,gson
            .toJson(generalOdfData))
        (activity as OrderDataFormActivity).setCurrentItem(1)

    }

    private fun currencyDialog() {

        val dialog = Dialog(requireContext())
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

        list.add(GeneralBottomAdapter.ListItemModel("Option 01"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 02"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 03"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 04"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 05"))

        val adapter = GeneralBottomAdapter(requireContext(), "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        tvSshowCurrency.text = "Option 01"
                    }
                    1 -> {
                        tvSshowCurrency.text = "Option 02"
                    }
                    2 -> {
                        tvSshowCurrency.text = "Option 03"
                    }
                    3 -> {
                        tvSshowCurrency.text = "Option 04"
                    }
                    4 -> {
                        tvSshowCurrency.text = "Option 05"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun dealForBranchDialog() {

        val dialog = Dialog(requireContext())
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

        list.add(GeneralBottomAdapter.ListItemModel("Option 01"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 02"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 03"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 04"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 05"))

        val adapter = GeneralBottomAdapter(requireContext(), "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        tvShowDealDetail.text = "Option 01"
                    }
                    1 -> {
                        tvShowDealDetail.text = "Option 02"
                    }
                    2 -> {
                        tvShowDealDetail.text = "Option 03"
                    }
                    3 -> {
                        tvShowDealDetail.text = "Option 04"
                    }
                    4 -> {
                        tvShowDealDetail.text = "Option 05"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun orderStatusDialog() {

        val dialog = Dialog(requireContext())
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

        list.add(GeneralBottomAdapter.ListItemModel("Option 01"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 02"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 03"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 04"))
        list.add(GeneralBottomAdapter.ListItemModel("Option 05"))

        val adapter = GeneralBottomAdapter(requireContext(), "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        tvShowOrderStatus.text = "Option 01"
                    }
                    1 -> {
                        tvShowOrderStatus.text = "Option 02"
                    }
                    2 -> {
                        tvShowOrderStatus.text = "Option 03"
                    }
                    3 -> {
                        tvShowOrderStatus.text = "Option 04"
                    }
                    4 -> {
                        tvShowOrderStatus.text = "Option 05"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    /*private fun etValidate(edittext: TextInputEditText): Boolean {
        var validate = edittext.text.toString()
        validate = validate.replace("\\s+".toRegex(), " ").trim { it <= ' ' }
        if (validate.isEmpty()) {
            edittext.error = "Required"
            return false
        }
        return true
    }*/

}