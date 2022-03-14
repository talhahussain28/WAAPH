package waaph.gb.com

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_signup1.*

class Signup1Activity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)
        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener(this)
        login.setOnClickListener(this)
        male.setOnClickListener(this)
        female.setOnClickListener(this)
    }
    @SuppressLint("ResourceAsColor")
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
                /*male.setBackgroundColor(R.color.ThemeColor)
                female.setBackgroundColor(R.color.white)*/
                male.setBackgroundResource(R.drawable.gender_male)
                female.setBackgroundResource(R.drawable.gender_female)
               // male.setBackgroundResource(R.drawable.gender_bg)

            }
            R.id.female ->{
                /*female.setBackgroundColor(R.color.ThemeColor)
                male.setBackgroundColor(R.color.white)*/
                female.setBackgroundResource(R.drawable.gender_female_selected)
                male.setBackgroundResource(R.drawable.gender_bg)


            }
        }
    }
}