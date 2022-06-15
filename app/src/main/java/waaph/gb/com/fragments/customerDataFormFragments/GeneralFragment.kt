package waaph.gb.com.fragments.customerDataFormFragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_general.*
import waaph.gb.com.CustomerDataFormActivity
import waaph.gb.com.R
import waaph.gb.com.adapters.GeneralAdapter
import waaph.gb.com.model.CreateGeneralModel
import waaph.gb.com.utils.BaseFragment
import waaph.gb.com.utils.GeneralBottomAdapter
import waaph.gb.com.utils.Utils

class GeneralFragment : BaseFragment(), View.OnClickListener {

    private lateinit var adapter: GeneralAdapter
    private lateinit var list: ArrayList<CreateGeneralModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        initialize()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreateGeneral -> {
                createGeneral()
            }
            R.id.customer -> {
                customerDialog()
            }
            R.id.business ->{
                businessDialog()
            }
            R.id.region -> {
                regionDialog()
            }
        }
    }


    private fun createGeneral() {
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
            (activity as CustomerDataFormActivity).setCurrentItem(1)
            Toast.makeText(requireContext(), "task done", Toast.LENGTH_SHORT).show()
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
                        tvRegion.text = "ASSOCIATES"
                    }
                    1 -> {
                        tvRegion.text = "CLRAGNTS"
                    }
                    2 -> {
                        tvRegion.text = "COMM"
                    }
                    3 -> {
                        tvRegion.text = "COMMISSION"
                    }
                    4 -> {
                        tvRegion.text = "CONS"
                    }
                    5 -> {
                        tvRegion.text = "CONSULT"
                    }

                    6 -> {
                        tvRegion.text = "CORP"
                    }
                    7 -> {
                        tvRegion.text = "DIST"
                    }
                    8 -> {
                        tvRegion.text = "EMPLOYEE"
                    }

                    9 -> {
                        tvRegion.text = "FARM"
                    }

                    10 -> {
                        tvRegion.text = "GOVT"
                    }

                    11 -> {
                        tvRegion.text = "INDENT"
                    }

                    12 -> {
                        tvRegion.text = "LOAN"
                    }

                    13 -> {
                        tvRegion.text = "ONLINERETA"
                    }

                    14 -> {
                        tvRegion.text = "OTHRRECBL"
                    }
                    15 -> {
                        tvRegion.text = "PRIV"
                    }
                    16 -> {
                        tvRegion.text = "RITA"
                    }
                    17 -> {
                        tvRegion.text = "SAMPLE"
                    }
                    18 -> {
                        tvRegion.text = "SEC DEP"
                    }
                    19 -> {
                        tvRegion.text = "SPAREPARTS"
                    }
                    20 -> {
                        tvRegion.text = "STCK"
                    }
                    21 -> {
                        tvRegion.text = "UN-IDENTIF"
                    }
                    22 -> {
                        tvRegion.text = "WALK-IN"
                    }
                    23 -> {
                        tvRegion.text = "WHOL"
                    }


                }
                dialog.dismiss()

            }
        dialog.show()

    }

}