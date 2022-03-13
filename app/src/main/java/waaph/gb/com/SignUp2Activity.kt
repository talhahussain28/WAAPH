package waaph.gb.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_sign_up2.*

class SignUp2Activity : AppCompatActivity(), View.OnClickListener, CountryCodePicker.OnCountryChangeListener {
    private var ccp:CountryCodePicker?=null
    private var countryCode:String?=null
    private var countryName:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)
        val next = findViewById<Button>(R.id.signUp)
        next.setOnClickListener(this)
        country_code_picker.setOnCountryChangeListener(this)
        country_code_picker.setDefaultCountryUsingNameCode("Pk")
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.signUp -> {
                val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCountrySelected() {
    }
}