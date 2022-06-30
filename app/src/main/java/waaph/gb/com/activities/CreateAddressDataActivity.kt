package waaph.gb.com.activities

import android.annotation.SuppressLint
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
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_create_address_data.*
import kotlinx.android.synthetic.main.activity_create_address_data.region
import kotlinx.android.synthetic.main.activity_create_address_data.regionList
import kotlinx.android.synthetic.main.activity_create_address_data.tvRegion
import kotlinx.android.synthetic.main.custom_dialog.*
import waaph.gb.com.R
import waaph.gb.com.entities.cdf.AddressEnt
import waaph.gb.com.model.SelectedDayCreateAddressModel
import waaph.gb.com.utils.*

class CreateAddressDataActivity : BaseActivity() , View.OnClickListener{

    private lateinit var selectDay : ArrayList<SelectedDayCreateAddressModel>
    private var model  : SelectedDayCreateAddressModel? = null
    private lateinit var days : TextView
    private var addressData: AddressEnt? = null

    private var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_address_data)
        initialize()
        setOnClickListener()
    }

    override fun linkXML() {
    }

    override fun setOnClickListener() {
        btnAddAddress.setOnClickListener(this)
        locationTypeList.setOnClickListener(this)
        countryList.setOnClickListener(this)
        regionList.setOnClickListener(this)
        areaLocationList.setOnClickListener(this)
        provinceList.setOnClickListener(this)
        tvMonday.setOnClickListener(this)
        tvSunday.setOnClickListener(this)
        tvSaturday.setOnClickListener(this)
        tvFriday.setOnClickListener(this)
        tvThursday.setOnClickListener(this)
        tvWednesday.setOnClickListener(this)
        tvTuesday.setOnClickListener(this)
        location.setOnClickListener(this)
        country.setOnClickListener(this)
        province.setOnClickListener(this)
        region.setOnClickListener(this)
        arealocation.setOnClickListener(this)
   }

    override fun initialize() {
        selectDay = ArrayList()
        days = tvShowDay
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAddAddress -> {
                addAddress()
                //(activity as CustomerDataFormActivity).setCurrentItem(3)
            }
            R.id.locationTypeList -> {
                locationTypeDialog()
            }
            R.id.countryList -> {
                countryDialog()
            }
            R.id.provinceList -> {
                provinceDialog()
            }
            R.id.regionList -> {
                regionDialog()
            }
            R.id.areaLocationList -> {
                areaLocationDialog()
            }
            R.id.location ->{
                locationTypeDialog()
            }
            R.id.country ->{
                countryDialog()
            }
            R.id.province ->{
                provinceDialog()
            }
            R.id.region -> {
                regionDialog()
            }
            R.id.arealocation ->{
                areaLocationDialog()
            }
            R.id.tvMonday -> {
                tvMonday.setBackgroundResource(R.drawable.days_selected_background_blue)
                tvMonday.setTextColor(R.color.white)
                tvShowDay.text = "Monday"
                var day = model?.selectAddress
                day = days.text.toString()
                selectDay.add(SelectedDayCreateAddressModel("${tvShowDay.text.toString()}"))
                Toast.makeText(this, "$day", Toast.LENGTH_SHORT).show()
                tvTuesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvWednesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvThursday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvFriday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSaturday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSunday.setBackgroundResource(R.drawable.days_selected_background_white)
            }
            R.id.tvTuesday -> {
                selectDay.clear()
                tvTuesday.setBackgroundResource(R.drawable.days_selected_background_blue)
                tvTuesday.setTextColor(R.color.white)
                var day = model?.selectAddress
                day = days.text.toString()
                tvShowDay.text = "Tuesday"
                selectDay.add(SelectedDayCreateAddressModel("${tvShowDay.text.toString()}"))
                Toast.makeText(this, "$day", Toast.LENGTH_SHORT).show()
                tvMonday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvWednesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvThursday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvFriday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSaturday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSunday.setBackgroundResource(R.drawable.days_selected_background_white)
            }
            R.id.tvWednesday -> {
                selectDay.clear()
                tvWednesday.setBackgroundResource(R.drawable.days_selected_background_blue)
                tvWednesday.setTextColor(R.color.white)
                tvShowDay.text = "Wednesday"
                var day = model?.selectAddress
                day = days.text.toString()
                selectDay.add(SelectedDayCreateAddressModel("${tvShowDay.text.toString()}"))
                Toast.makeText(this, "$day", Toast.LENGTH_SHORT).show()
                tvTuesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvMonday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvThursday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvFriday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSaturday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSunday.setBackgroundResource(R.drawable.days_selected_background_white)
            }
            R.id.tvThursday -> {
                selectDay.clear()
                tvThursday.setBackgroundResource(R.drawable.days_selected_background_blue)
                tvThursday.setTextColor(R.color.white)
                tvShowDay.text = "Thursday"
                var day = model?.selectAddress
                day = days.text.toString()
                selectDay.add(SelectedDayCreateAddressModel("${tvShowDay.text.toString()}"))
                Toast.makeText(this, "$day", Toast.LENGTH_SHORT).show()
                tvTuesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvWednesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvMonday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvFriday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSaturday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSunday.setBackgroundResource(R.drawable.days_selected_background_white)
            }
            R.id.tvFriday -> {
                selectDay.clear()
                tvFriday.setBackgroundResource(R.drawable.days_selected_background_blue)
                tvFriday.setTextColor(R.color.white)
                tvShowDay.text = "Friday"
                var day = model?.selectAddress
                day = days.text.toString()
                selectDay.add(SelectedDayCreateAddressModel("${tvShowDay.text.toString()}"))
                Toast.makeText(this, "$day", Toast.LENGTH_SHORT).show()
                tvTuesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvWednesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvThursday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvMonday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSaturday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSunday.setBackgroundResource(R.drawable.days_selected_background_white)
            }
            R.id.tvSaturday -> {
                selectDay.clear()
                tvSaturday.setBackgroundResource(R.drawable.days_selected_background_blue)
                tvSaturday.setTextColor(R.color.white)
                tvShowDay.text = "Saturday"
                tvShowDay.visibility = View.VISIBLE
                selectDay.add(SelectedDayCreateAddressModel("${tvShowDay.text.toString()}"))
                val day = model?.selectAddress
                Toast.makeText(this, "$day", Toast.LENGTH_SHORT).show()
                tvTuesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvWednesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvThursday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvFriday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvMonday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSunday.setBackgroundResource(R.drawable.days_selected_background_white)
            }
            R.id.tvSunday -> {
                selectDay.clear()
                tvSunday.setTextColor(R.color.white)
                tvShowDay.text = "Sunday"
                var day = model?.selectAddress
                day = days.text.toString()
                selectDay.add(SelectedDayCreateAddressModel("${tvShowDay.text.toString()}"))
                Toast.makeText(this, "$day", Toast.LENGTH_SHORT).show()
                tvSunday.setBackgroundResource(R.drawable.days_selected_background_blue)
                tvTuesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvWednesday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvThursday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvFriday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvSaturday.setBackgroundResource(R.drawable.days_selected_background_white)
                tvMonday.setBackgroundResource(R.drawable.days_selected_background_white)
            }
        }
    }

    private fun locationTypeDialog() {

        val dialog = Dialog(this)
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

        list.add(GeneralBottomAdapter.ListItemModel("MULTI LOCATION"))
        list.add(GeneralBottomAdapter.ListItemModel("KARACHI HEAD OFFICE"))
        list.add(GeneralBottomAdapter.ListItemModel("KARACHI MAIN OFFICE"))
        list.add(GeneralBottomAdapter.ListItemModel("KARACHI KORANGI OFFICE"))
        list.add(GeneralBottomAdapter.ListItemModel("KARACHI CHANDNI CHOWK"))
        list.add(GeneralBottomAdapter.ListItemModel("KARACHI PORT QASIM"))
        list.add(GeneralBottomAdapter.ListItemModel("KARACHI PAKISTAN CHOWK"))
        list.add(GeneralBottomAdapter.ListItemModel("KARACHI SALES OFFICE-I"))
        list.add(GeneralBottomAdapter.ListItemModel("LAHORE OFFICE"))
        list.add(GeneralBottomAdapter.ListItemModel("LAHORE WAREHOUSE"))
        list.add(GeneralBottomAdapter.ListItemModel("MULTAN OFFICE"))
        list.add(GeneralBottomAdapter.ListItemModel("FAISALABAD OFFICE"))
        list.add(GeneralBottomAdapter.ListItemModel("RAWALPINDI OFFICE"))
        list.add(GeneralBottomAdapter.ListItemModel("ISLAMABAD WAREHOUSE"))
        list.add(GeneralBottomAdapter.ListItemModel("HYDERABAD OFFICE"))
        list.add(GeneralBottomAdapter.ListItemModel("GUJRANWALA WAREHOUSE"))
        list.add(GeneralBottomAdapter.ListItemModel("SEMEN PRODUCTION UNIT-"))

        val adapter = GeneralBottomAdapter(this, "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        locationType.text = "MULTI LOCATION"
                    }
                    1 -> {
                        locationType.text = "KARACHI HEAD OFFICE"
                    }
                    2 -> {
                        locationType.text = "KARACHI MAIN OFFICE"
                    }
                    3 -> {
                        locationType.text = "KARACHI KORANGI OFFICE"
                    }
                    4 -> {
                        locationType.text = "KARACHI CHANDNI CHOWK"
                    }
                    5 -> {
                        locationType.text = "KARACHI PORT QASIM"
                    }
                    6 -> {
                        locationType.text = "KARACHI PAKISTAN CHOWK"
                    }
                    7 -> {
                        locationType.text = "KARACHI SALES OFFICE-I"
                    }
                    8 -> {
                        locationType.text = "LAHORE OFFICE"
                    }
                    9 -> {
                        locationType.text = "LAHORE WAREHOUSE"
                    }
                    10 -> {
                        locationType.text = "MULTAN OFFICE"
                    }
                    11-> {
                        locationType.text = "FAISALABAD OFFICE"
                    }
                    12 -> {
                        locationType.text = "RAWALPINDI OFFICE"
                    }
                    13 -> {
                        locationType.text = "ISLAMABAD WAREHOUSE"
                    }
                    14 -> {
                        locationType.text = "HYDERABAD OFFICE"
                    }
                    15 -> {
                        locationType.text = "GUJRANWALA WAREHOUSE"
                    }
                    16 -> {
                        locationType.text = "SEMEN PRODUCTION UNIT-"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun provinceDialog() {

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

        list.add(GeneralBottomAdapter.ListItemModel("Sindh"))
        list.add(GeneralBottomAdapter.ListItemModel("Punjab"))
        list.add(GeneralBottomAdapter.ListItemModel("KPK"))
        list.add(GeneralBottomAdapter.ListItemModel("Balochistan"))
        list.add(GeneralBottomAdapter.ListItemModel("Gilgit"))

        val adapter = GeneralBottomAdapter(this, "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        tvProvince.text = "Sindh"
                    }
                    1 -> {
                        tvProvince.text = "Punjab"
                    }
                    2 -> {
                        tvProvince.text = "KPK"
                    }
                    3 -> {
                        tvProvince.text = "Balochistan"
                    }
                    4 -> {
                        tvProvince.text = "Gilgit"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun regionDialog() {

        val dialog = Dialog(this)
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

        val adapter = GeneralBottomAdapter(this, "", list)
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

    private fun areaLocationDialog() {

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
                        tvLocation.text = "Option 1"
                    }
                    1 -> {
                        tvLocation.text = "Option 2"
                    }
                    2 -> {
                        tvLocation.text = "Option 3"
                    }
                    3 -> {
                        tvLocation.text = "Option 4"
                    }
                    4 -> {
                        tvLocation.text = "Option 5"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun countryDialog() {

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

        list.add(GeneralBottomAdapter.ListItemModel("Pakistan"))
        list.add(GeneralBottomAdapter.ListItemModel("India"))
        list.add(GeneralBottomAdapter.ListItemModel("Turkey"))
        list.add(GeneralBottomAdapter.ListItemModel("Dubai"))
        list.add(GeneralBottomAdapter.ListItemModel("Australia"))

        val adapter = GeneralBottomAdapter(this, "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        tvCountry.text = "Pakistan"
                    }
                    1 -> {
                        tvCountry.text = "India"
                    }
                    2 -> {
                        tvCountry.text = "Turkey"
                    }
                    3 -> {
                        tvCountry.text = "Dubai"
                    }
                    4 -> {
                        tvCountry.text = "Australia"
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    private fun addAddress(){

        val addressType = if (customSwitchSTN.isChecked){
            "0"
        }else{
            "1"
        }

        Utils.etValidate(edtAddressName)
        Utils.etValidate(edtAddressLine)
        Utils.etValidate(edtPostalCode)
        Utils.etValidate(edtWorkingHours)

        if (edtAddressName.text!!.isNotEmpty()&&
            edtAddressLine.text!!.isNotEmpty()&&
            edtPostalCode.text!!.isNotEmpty()&&
            edtWorkingHours.text!!.isNotEmpty()
        ) {
            addressData = AddressEnt(
                0,
                0,
                edtAddressName.getTextToString(),
                addressType,
                "",
                edtAddressLine.getTextToString(),
                "",
                "",
                "",
                "",
                "",
                edtPostalCode.getTextToString(),
                ArrayList(),
                edtWorkingHours.getTextToString().toLong(),
                0L,
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
                false,
                false
            )

            var intent = Intent()
            intent.putExtra("data", gson.toJson(addressData))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}