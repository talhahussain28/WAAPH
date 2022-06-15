package waaph.gb.com.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.hbb20.CountryCodePicker
import com.hbb20.CountryPickerView
import com.hbb20.countrypicker.models.CPCountry
import kotlinx.android.synthetic.main.activity_sign_up2.*
import waaph.gb.com.R
import waaph.gb.com.utils.BaseActivity

class SignUp2Activity : BaseActivity(), View.OnClickListener {
    private var ccp:CountryCodePicker?=null
    private var countryCode:String?=null
    private var countryName:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)
        setOnClickListener()
        initialize()

    }

    override fun linkXML() {

    }

    override fun setOnClickListener() {
        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener(this)
        login.setOnClickListener(this)
    }

    override fun initialize() {
        val countryPicker = findViewById<CountryPickerView>(R.id.country_picker)

        // Modify CPViewConfig if you need. Access cpViewConfig through `cpViewHelper`
        countryPicker.cpViewHelper.cpViewConfig.viewTextGenerator = { cpCountry: CPCountry ->
            "${cpCountry.name} (${cpCountry.alpha2})"
        }
        // make sure to refresh view once view configuration is changed
        countryPicker.cpViewHelper.refreshView()

        /*country_code_picker.setOnCountryChangeListener(this)
        country_code_picker.setDefaultCountryUsingNameCode("Pk")*/
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {
                val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
            }
            R.id.login -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

   /* override fun onCountrySelected() {
    }*/
}