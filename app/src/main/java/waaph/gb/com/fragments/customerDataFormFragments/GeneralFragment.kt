package waaph.gb.com.fragments.customerDataFormFragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_general.*
import waaph.gb.com.R
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.adapters.GeneralAdapter
import waaph.gb.com.database.cdf.GeneralDatabase
import waaph.gb.com.entities.cdf.GeneralEnt
import waaph.gb.com.model.CreateGeneralModel
import waaph.gb.com.utils.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_customer_data_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import waaph.gb.com.entities.cdf.GetAllRegionResponse
import waaph.gb.com.entities.cdf.RegionData
import waaph.gb.com.network.ServiceUtils
import waaph.gb.com.responses.CustomerGroupListData
import waaph.gb.com.responses.CustomerGroupResponse
import waaph.gb.com.utils.Constants.Companion.ARG_GENERAL


class GeneralFragment : BaseFragment(), View.OnClickListener {

    private lateinit var adapter: GeneralAdapter
    private lateinit var list: ArrayList<CreateGeneralModel>
    private lateinit var generalDatabase: GeneralDatabase
    private var generalData : GeneralEnt? = null

    private var regionList: ArrayList<RegionData>? = ArrayList()
    private var customerGroupList: ArrayList<CustomerGroupListData>? = ArrayList()

    private var prefs: SaveInSharedPreference? = null
    private var gson = Gson()
    private var selectedRegion: RegionData? = null
    private var selectedCustomer: CustomerGroupListData? = null

    private var isUpdate = false
    private var isSTRN = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disableOtherTabsExcept(0)
        prefs = SaveInSharedPreference(requireContext())

        generalDatabase = GeneralDatabase.getInstance(context!!)

        setOnClickListener()
        initialize()
    }

    private fun disableOtherTabsExcept(currentTabIndex: Int) {
        for (i in 0..6){
            (activity as CustomerDataFormActivity).tabLayout.getTabAt(i)?.view!!.isClickable = false
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreateGeneral -> {
                hideKeyBoard(edtOrganizationName)
                createGeneralItem()
            }
            R.id.customer -> {
                customerGroupList.let {
                    if (it != null) {
                        customerListDialog(it)
                    }
                }
            }
            R.id.business -> {
//                businessDialog()
            }
            R.id.region -> {
                regionList.let {
                    if (it != null) {
                        regionDialog(it)
                    }
                }

            }
        }
    }

    override fun linkXML(view: View?) {
    }

    override fun setOnClickListener() {
        btnCreateGeneral.setOnClickListener(this)
        customer.setOnClickListener(this)
        business.setOnClickListener(this)
        region.setOnClickListener(this)
    }

    override fun initialize() {
/*        list.add(CreateGeneralModel("Miletap"))
        list.add(CreateGeneralModel("LuteBox"))
        list.add(CreateGeneralModel("Pnc solution"))
        list.add(CreateGeneralModel("Ibex"))
        list.add(CreateGeneralModel("rajby"))*/

        setTextWatchers()
        setCreateButtonStatus()

        getAllCustomerGroup()
        getAllRegion()
    }

    private fun setTextWatchers() {
        edtBussinessName.addTextChangedListener(textWatcher)
        edtCNIC.addTextChangedListener(textWatcher)
        edtNTN.addTextChangedListener(textWatcher)
        edtPhone.addTextChangedListener(textWatcher)
        edtFAX.addTextChangedListener(textWatcher)
        edtMobile.addTextChangedListener(textWatcher)
        edtWhatsApp.addTextChangedListener(textWatcher)
        edtWebSite.addTextChangedListener(textWatcher)
        edtEmail.addTextChangedListener(textWatcher)
        edtOrganizationName.addTextChangedListener(textWatcher)
    }


    private fun validateEditTexts() {
        Utils.etValidate(edtBussinessName)
        Utils.etValidate(edtCNIC)
        Utils.etValidate(edtNTN)

        Utils.etValidate(edtFAX)
        Utils.etValidate(edtMobile)
        Utils.etValidate(edtWhatsApp)
        Utils.etValidate(edtWebSite)
        Utils.etValidate(edtEmail)
        Utils.etValidate(edtOrganizationName)
        Utils.etValidate(edtPhone)

        if (edtBussinessName.text!!.isNotEmpty() &&
            /* edtCustomerGroup.text!!.isNotEmpty()&&*/
            /* edtBussinessType.text!!.isNotEmpty() &&*/
            edtCNIC.text!!.isNotEmpty() &&
            edtNTN.text!!.isNotEmpty() &&
            /* edtRegion.text!!.isNotEmpty() &&*/
            edtFAX.text!!.isNotEmpty() &&
            edtMobile.text!!.isNotEmpty() &&
            edtWhatsApp.text!!.isNotEmpty() &&
            edtWebSite.text!!.isNotEmpty() &&
            edtEmail.text!!.isNotEmpty() &&
            edtOrganizationName.text!!.isNotEmpty() &&
            edtPhone.text!!.isNotEmpty()
        ) {
            btnCreateGeneral.show()
            setCreateButtonStatus()
            /*(activity as CustomerDataFormActivity).setCurrentItem(1)
            Toast.makeText(requireContext(), "task done", Toast.LENGTH_SHORT).show()*/
        }else {
            btnCreateGeneral.gone()
        }
    }

    private fun setCreateButtonStatus() {
        /*if (isUpdate){
            btnCreateGeneral.text = "Update"
        }else {
            btnCreateGeneral.text = "Create"
        }*/
    }

    private fun createGeneralItem() {

        isSTRN = if (customSwitchSTRN.isChecked){
            "yes"
        }else{
            "no"
        }

            generalData = GeneralEnt(0,
                    0,
                   0,
                    edtBussinessName.text.toString(),
                    ",",
                    tvCustomerGroup.text.toString(),
                    tvBusinessType.text.toString(),
                    edtCNIC.text.toString(),
                    edtNTN.text.toString(),
                    isSTRN,
                    tvRegion.text.toString(),
                    edtPhone.text.toString(),
                    edtFAX.text.toString(),
                    edtMobile.text.toString(),
                    edtWhatsApp.text.toString(),
                    edtWebSite.text.toString(),
                    edtEmail.text.toString(),
                    customSwitch.isChecked,
                    edtOrganizationName.text.toString(),
                    "",
                    "",
                    "",
                    0L,
                    "",
                    0L,
                    "",
                    0L,
                    "",
                    0L,
                    "",
                    true,
                    true
                )

        prefs?.setString(
            ARG_GENERAL,
            gson.toJson(generalData)
        )
        (activity as CustomerDataFormActivity).setCurrentItem(1)

        /*generalData = Gson().fromJson(
            SaveInSharedPreference(requireContext()).getString(Constants.ARG_GENERAL),
            GeneralEnt::class.java
        )*/
    }


    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {

            // TODO: Temp Code
            btnCreateGeneral.show()
            setCreateButtonStatus()

//            validateEditTexts()
        }
    }

    private fun getAllRegion() {
        val call = ServiceUtils.createService()
            .allRegion
        call.enqueue(object : Callback<GetAllRegionResponse>{
            override fun onResponse(
                call: Call<GetAllRegionResponse>,
                response: Response<GetAllRegionResponse>
            ) {
                if (response.isSuccessful){
                    regionList = response.body()?.Data ?: ArrayList()
                }else {
                    // error case
                    when (response.code()) {
                        404 -> setLog("TAG", "not found")
                        500 -> setLog("TAG", "server broken")
                        else -> setLog("TAG", "unknown error")
                    }
                }
            }

            override fun onFailure(call: Call<GetAllRegionResponse>, t: Throwable) {
                setLog("TAG", "Failure")
            }

        })
    }

    private fun businessDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation_2
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        val list: MutableList<GeneralBottomAdapter.ListItemModel> = ArrayList()
        list.add(GeneralBottomAdapter.ListItemModel("B2B"))
        list.add(GeneralBottomAdapter.ListItemModel("B2C"))
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        tvBusinessType.text = "B2B"
                    }
                    1 -> {
                        tvBusinessType.text = "B2C"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun regionDialog(list: List<RegionData>) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation_2
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            width
        )

        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        val adapterList = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1,android.R.id.text1,list)
        dialog.listView.adapter = adapterList
        dialog.listView.setOnItemClickListener{parent, view, position, id ->
            selectedRegion = parent.getItemAtPosition(position) as RegionData
            tvRegion.text = selectedRegion.toString()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun getAllCustomerGroup() {
        val call = ServiceUtils.createService()
            .customerGroup
        call.enqueue(object : Callback<CustomerGroupResponse>{
            override fun onResponse(
                call: Call<CustomerGroupResponse>,
                response: Response<CustomerGroupResponse>
            ) {
                if (response.isSuccessful){
                    customerGroupList = response.body()?.Data ?: ArrayList()
                }else {
                    // error case
                    when (response.code()) {
                        404 -> setLog("TAG", "not found")
                        500 -> setLog("TAG", "server broken")
                        else -> setLog("TAG", "unknown error")
                    }
                }
            }

            override fun onFailure(call: Call<CustomerGroupResponse>, t: Throwable) {
                setLog("TAG", "Failure")
            }

        })
    }

    private fun customerListDialog(list: List<CustomerGroupListData>){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation_2
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            width
        )

        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        val adapterList = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1,android.R.id.text1,list)
        dialog.listView.adapter = adapterList
        dialog.listView.setOnItemClickListener{parent, view, position, id ->
            selectedCustomer = parent.getItemAtPosition(position) as CustomerGroupListData
            tvCustomerGroup.text = selectedCustomer.toString()
            dialog.dismiss()
        }
        dialog.show()
    }

}