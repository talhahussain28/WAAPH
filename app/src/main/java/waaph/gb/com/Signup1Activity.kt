package waaph.gb.com

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_signup1.*
import waaph.gb.com.utils.BaseActivity

class Signup1Activity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)
        initialize()
        setOnClickListener()
    }

    override fun linkXML() {

    }

    override fun setOnClickListener() {
        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener(this)
        login.setOnClickListener(this)
        male.setOnClickListener(this)
        female.setOnClickListener(this)

    }

    override fun initialize() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {
                val intent = Intent(this, SignUp2Activity::class.java)
                startActivity(intent)
            }
            R.id.login -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.male ->{
                male.setTextColor(Color.parseColor("#FFFFFF"))
                female.setTextColor(Color.parseColor("#A8A8A8"))
                male.setBackgroundResource(R.drawable.gender_male)
                female.setBackgroundResource(R.drawable.gender_female)

            }
            R.id.female ->{
               female.setTextColor(Color.parseColor("#FFFFFF"))
                male.setTextColor(Color.parseColor("#A8A8A8"))

                female.setBackgroundResource(R.drawable.gender_female_selected)
                male.setBackgroundResource(R.drawable.gender_bg)


            }
        }
    }
}