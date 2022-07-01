package waaph.gb.com.fragments.customerDataFormFragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_general.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import waaph.gb.com.R
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.adapters.GeneralAdapter
import waaph.gb.com.database.cdf.GeneralDatabase
import waaph.gb.com.entities.cdf.GeneralEnt
import waaph.gb.com.model.CreateGeneralModel
import waaph.gb.com.utils.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_customer_data_form.*
import waaph.gb.com.utils.Constants.Companion.ARG_GENERAL


class GeneralFragment : BaseFragment(), View.OnClickListener {

    private lateinit var adapter: GeneralAdapter
    private lateinit var list: ArrayList<CreateGeneralModel>
    private lateinit var generalDatabase: GeneralDatabase
    private var generalData : GeneralEnt? = null

    private var prefs: SaveInSharedPreference? = null
    private var gson = Gson()

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

//        checkDatabase()
        setOnClickListener()
        initialize()
    }

    private fun disableOtherTabsExcept(currentTabIndex: Int) {
        for (i in 1..6){
            (activity as CustomerDataFormActivity).tabLayout.getTabAt(i)?.view!!.isClickable = false
        }

    }

    private fun checkDatabase() {
        CoroutineScope(Dispatchers.IO).async{
            generalData = generalDatabase.generalDao.getGeneralSingle(0)
            if (generalData!!.businessName.isNullOrEmpty()){
                isUpdate = false
                btnCreateGeneral.gone()
            }else{
                isUpdate = true
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreateGeneral -> {
                createGeneralItem()
            }
            R.id.customer -> {
                customerDialog()
            }
            R.id.business -> {
                businessDialog()
            }
            R.id.region -> {
                regionDialog()
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
            validateEditTexts()
        }
    }

    private fun customerDialog() {

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

        val listView = dialog.findViewById<ListView>(R.id.listView)
        val list: MutableList<GeneralBottomAdapter.ListItemModel> = ArrayList()

        list.add(GeneralBottomAdapter.ListItemModel("ASSOCIATES"))
        list.add(GeneralBottomAdapter.ListItemModel("CLRAGNTS"))
        list.add(GeneralBottomAdapter.ListItemModel("COMM"))
        list.add(GeneralBottomAdapter.ListItemModel("COMMISSION"))
        list.add(GeneralBottomAdapter.ListItemModel("CONS"))
        list.add(GeneralBottomAdapter.ListItemModel("CONSULT"))
        list.add(GeneralBottomAdapter.ListItemModel("CORP"))
        list.add(GeneralBottomAdapter.ListItemModel("DIST"))
        list.add(GeneralBottomAdapter.ListItemModel("EMPLOYEE"))
        list.add(GeneralBottomAdapter.ListItemModel("FARM"))
        list.add(GeneralBottomAdapter.ListItemModel("FEED"))
        list.add(GeneralBottomAdapter.ListItemModel("GOVT"))
        list.add(GeneralBottomAdapter.ListItemModel("INDENT"))
        list.add(GeneralBottomAdapter.ListItemModel("ONLINERETA"))
        list.add(GeneralBottomAdapter.ListItemModel("OTHRRECBL"))
        list.add(GeneralBottomAdapter.ListItemModel("PRIV"))
        list.add(GeneralBottomAdapter.ListItemModel("RETA"))
        list.add(GeneralBottomAdapter.ListItemModel("SAMPLE"))
        list.add(GeneralBottomAdapter.ListItemModel("SEC DEP"))
        list.add(GeneralBottomAdapter.ListItemModel("SPAREPARTS"))
        list.add(GeneralBottomAdapter.ListItemModel("STCK"))
        list.add(GeneralBottomAdapter.ListItemModel("UN-IDENTIF"))
        list.add(GeneralBottomAdapter.ListItemModel("WALK-IN"))
        list.add(GeneralBottomAdapter.ListItemModel("WHOL"))


        val adapter = GeneralBottomAdapter(requireContext(), "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        tvCustomerGroup.text = "ASSOCIATES"
                    }
                    1 -> {
                        tvCustomerGroup.text = "CLRAGNTS"
                    }
                    2 -> {
                        tvCustomerGroup.text = "COMM"
                    }
                    3 -> {
                        tvCustomerGroup.text = "COMMISSION"
                    }
                    4 -> {
                        tvCustomerGroup.text = "CONS"
                    }
                    5 -> {
                        tvCustomerGroup.text = "CONSULT"
                    }

                    6 -> {
                        tvCustomerGroup.text = "CORP"
                    }
                    7 -> {
                        tvCustomerGroup.text = "DIST"
                    }
                    8 -> {
                        tvCustomerGroup.text = "EMPLOYEE"
                    }

                    9 -> {
                        tvCustomerGroup.text = "FARM"
                    }

                    10 -> {
                        tvCustomerGroup.text = "GOVT"
                    }

                    11 -> {
                        tvCustomerGroup.text = "INDENT"
                    }

                    12 -> {
                        tvCustomerGroup.text = "LOAN"
                    }

                    13 -> {
                        tvCustomerGroup.text = "ONLINERETA"
                    }

                    14 -> {
                        tvCustomerGroup.text = "OTHRRECBL"
                    }
                    15 -> {
                        tvCustomerGroup.text = "PRIV"
                    }
                    16 -> {
                        tvCustomerGroup.text = "RITA"
                    }
                    17 -> {
                        tvCustomerGroup.text = "SAMPLE"
                    }
                    18 -> {
                        tvCustomerGroup.text = "SEC DEP"
                    }
                    19 -> {
                        tvCustomerGroup.text = "SPAREPARTS"
                    }
                    20 -> {
                        tvCustomerGroup.text = "STCK"
                    }
                    21 -> {
                        tvCustomerGroup.text = "UN-IDENTIF"
                    }
                    22 -> {
                        tvCustomerGroup.text = "WALK-IN"
                    }
                    23 -> {
                        tvCustomerGroup.text = "WHOL"
                    }


                }
                dialog.dismiss()

            }
        dialog.show()

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

        val listView = dialog.findViewById<ListView>(R.id.listView)
        val list: MutableList<GeneralBottomAdapter.ListItemModel> = ArrayList()

        list.add(GeneralBottomAdapter.ListItemModel("B2B"))
        list.add(GeneralBottomAdapter.ListItemModel("B2C"))
        /*   list.add(GeneralBottomAdapter.ListItemModel("COMM"))
           list.add(GeneralBottomAdapter.ListItemModel("COMMISSION"))
           list.add(GeneralBottomAdapter.ListItemModel("CONS"))*/


        val adapter = GeneralBottomAdapter(requireContext(), "", list)
        listView.adapter = adapter
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
                        /*   }
                    2 -> {
                        tvCustomerGroup.text = "COMM"
                    }
                    3 -> {
                        tvCustomerGroup.text = "COMMISSION"
                    }*/


                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun regionDialog() {

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

        val listView = dialog.findViewById<ListView>(R.id.listView)
        val list: MutableList<GeneralBottomAdapter.ListItemModel> = ArrayList()

        list.add(GeneralBottomAdapter.ListItemModel("FAISALABAD-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("LAHORE-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("GUJRANWALA-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("MIRPUR KHAS-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("NAWABSHAH-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("PESHAWAR-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("KAMALIA-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("FAISALABAD-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("LAHORE-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("GUJRANWALA-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("MULTAN-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("KARACHI-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("HYDERABAD-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("RAWALPINDI-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("QUETTA-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("SUKKUR-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("SAHIWAL-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("MIRPUR KHAS-DEP"))
        list.add(GeneralBottomAdapter.ListItemModel("NAWABSHAH-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("PESHAWAR-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("KAMALIA-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("LAHORE 1-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("LAHORE 2-PHD"))
        list.add(GeneralBottomAdapter.ListItemModel("MULTAN-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("KARACHI-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("HYDERABAD-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("RAWALPINDI-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("QUETTA-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("SUKKUR-FTD"))
        list.add(GeneralBottomAdapter.ListItemModel("SAHIWAL-FTD"))


        val adapter = GeneralBottomAdapter(requireContext(), "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        tvRegion.text = "FAISALABAD-FTD"
                    }
                    1 -> {
                        tvRegion.text = "LAHORE-FTD"
                    }
                    2 -> {
                        tvRegion.text = "GUJRANWALA-FTD"
                    }
                    3 -> {
                        tvRegion.text = "MULTAN-PHD"
                    }
                    4 -> {
                        tvRegion.text = "KARACHI-PHD"
                    }
                    5 -> {
                        tvRegion.text = "HYDERABAD-PHD"
                    }
                    6 -> {
                        tvRegion.text = "RAWALPINDI-PHD"
                    }
                    7 -> {
                        tvRegion.text = "QUETTA-PHD"
                    }
                    8 -> {
                        tvRegion.text = "SUKKUR-PHD"
                    }

                    9 -> {
                        tvRegion.text = "SAHIWAL-PHD"
                    }

                    10 -> {
                        tvRegion.text = "MIRPUR KHAS-"
                    }

                    11 -> {
                        tvRegion.text = "NAWABSHAH-PHD"
                    }

                    12 -> {
                        tvRegion.text = "PESHAWAR-PHD"
                    }

                    13 -> {
                        tvRegion.text = "KAMALIA-PHD"
                    }

                    14 -> {
                        tvRegion.text = "LAHORE 1-PHD"
                    }
                    15 -> {
                        tvRegion.text = "LAHORE 2-PHD"
                    }
                    16 -> {
                        tvRegion.text = "FAISALABAD-SPD"
                    }
                    17 -> {
                        tvRegion.text = "LAHORE-SPD"
                    }
                    18 -> {
                        tvRegion.text = "GUJRANWALA-SPD"
                    }
                    19 -> {
                        tvRegion.text = "MULTAN-SPD"
                    }
                    20 -> {
                        tvRegion.text = "KARACHI-SPD"
                    }
                    21 -> {
                        tvRegion.text = "HYDERABAD-SPD"
                    }
                    22 -> {
                        tvRegion.text = "RAWALPINDI-SPD"
                    }
                    23 -> {
                        tvRegion.text = "QUETTA-SPD"
                    }
                    24 -> {
                        tvRegion.text = "SUKKUR-SPD"
                    }
                    25 -> {
                        tvRegion.text = "SAHIWAL-SPD"
                    }
                    26 -> {
                        tvRegion.text = "MIRPUR KHAS-PHD"
                    }
                    27 -> {
                        tvRegion.text = "NAWABSHAH-SPD"
                    }
                    28 -> {
                        tvRegion.text = "PESHAWAR-SPD"
                    }
                    29 -> {
                        tvRegion.text = "KAMALIA-SPD"
                    }
                    30 -> {
                        tvRegion.text = "FAISALABAD-AHD"
                    }
                    31 -> {
                        tvRegion.text = "LAHORE-AHD"
                    }
                    ///FTD rem
                    32 -> {
                        tvRegion.text = "MULTAN-FTD"
                    }
                    33 -> {
                        tvRegion.text = "KARACHI-FTD"
                    }
                    34 -> {
                        tvRegion.text = "HYDERABAD-FTD"
                    }
                    35 -> {
                        tvRegion.text = "RAWALPINDI-FTD"
                    }
                    36 -> {
                        tvRegion.text = "QUETTA-FTD"
                    }
                    37 -> {
                        tvRegion.text = "SUKKUR-FTD"
                    }
                    38 -> {
                        tvRegion.text = "SAHIWAL-FTD"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

}