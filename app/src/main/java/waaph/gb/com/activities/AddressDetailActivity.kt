package waaph.gb.com.activities

import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_address_detail.*
import waaph.gb.com.R
import waaph.gb.com.entities.cdf.AddressEnt
import waaph.gb.com.utils.BaseActivity

class AddressDetailActivity :BaseActivity() {
    val gson = Gson()
    lateinit var AddressData: AddressEnt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_detail)
        intent.extras?.let {
            AddressData = gson.fromJson(it.getString("data"), AddressEnt::class.java)
        }
        initialize()


    }

    override fun linkXML() {
    }

    override fun setOnClickListener() {
    }

    override fun initialize() {
        textViewaddress.text = AddressData.addressName
        textViewLocation.text = AddressData.locationType
        textViewAddressLine.text = AddressData.addressLine
        textViewCountry.text = AddressData.country
        textViewState.text = AddressData.state
        //textViewAreaLocation.text =
        textViewPostalCode.text = AddressData.postalCode
        textViewHours.text = AddressData.workingHoursTo.toString()
        textViewDays.text = AddressData.workingHoursTo.toString()

    }
}