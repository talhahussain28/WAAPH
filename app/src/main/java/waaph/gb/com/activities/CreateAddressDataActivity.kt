package waaph.gb.com.activities

import android.annotation.SuppressLint
import android.app.Dialog
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
import kotlinx.android.synthetic.main.activity_create_address_data.*
import kotlinx.android.synthetic.main.custom_dialog.*
import waaph.gb.com.R
import waaph.gb.com.model.SelectedDayCreateAddressModel
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.GeneralBottomAdapter
import waaph.gb.com.utils.Utils

class CreateAddressDataActivity : BaseActivity() , View.OnClickListener{

    private lateinit var selectDay : ArrayList<SelectedDayCreateAddressModel>
    private var model  : SelectedDayCreateAddressModel? = null
    private lateinit var days : TextView

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
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        val listView = dialog.findViewById<ListView>(R.id.listView)
        val list: MutableList<GeneralBottomAdapter.ListItemModel> = ArrayList()

        list.add(GeneralBottomAdapter.ListItemModel("Static 01"))
        list.add(GeneralBottomAdapter.ListItemModel("Static 02"))
        list.add(GeneralBottomAdapter.ListItemModel("Static 03"))
        list.add(GeneralBottomAdapter.ListItemModel("Static 04"))
        list.add(GeneralBottomAdapter.ListItemModel("Static 05"))

        val adapter = GeneralBottomAdapter(this, "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        locationType.text = "Static 01"
                    }
                    1 -> {
                        locationType.text = "Static 02"
                    }
                    2 -> {
                        locationType.text = "Static 03"
                    }
                    3 -> {
                        locationType.text = "Static 04"
                    }
                    4 -> {
                        locationType.text = "Static 05"
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
                        tvRegion.text = "Option 1"
                    }
                    1 -> {
                        tvRegion.text = "Option 2"
                    }
                    2 -> {
                        tvRegion.text = "Option 3"
                    }
                    3 -> {
                        tvRegion.text = "Option 4"
                    }
                    4 -> {
                        tvRegion.text = "Option 5"
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

        Utils.etValidate(edtAddressName)
        Utils.etValidate(edtAddressLine)
        Utils.etValidate(edtPostalCode)
        Utils.etValidate(edtWorkingHours)

        if (edtAddressName.text!!.isNotEmpty()&&
            edtAddressLine.text!!.isNotEmpty()&&
            edtPostalCode.text!!.isNotEmpty()&&
            edtWorkingHours.text!!.isNotEmpty()
        ) {
            Toast.makeText(this, "task done", Toast.LENGTH_SHORT).show()
        }
    }


}