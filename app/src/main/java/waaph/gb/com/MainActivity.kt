package waaph.gb.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.edit_text_password
import kotlinx.android.synthetic.main.activity_sign_up.*
import waaph.gb.com.utils.EditTextDrawableClick

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var isVisiblePassword = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener(this)
        tv_signup.setOnClickListener(this)
        forgot_pass.setOnClickListener(this)
        edit_text_password.setOnClickListener(this)
        edit_text_password.setDrawableClickListener { target ->
            when (target) {
                EditTextDrawableClick.DrawableClickListener.DrawablePosition.LEFTT -> {
                }
                EditTextDrawableClick.DrawableClickListener.DrawablePosition.RIGHTR -> {
                    isVisiblePassword = !isVisiblePassword
                    if (isVisiblePassword) {
                        // Show
                        edit_text_password.transformationMethod = null
                    } else {
                        // Hide
                        edit_text_password.transformationMethod = PasswordTransformationMethod()
                    }
                    edit_text_password.setSelection(edit_text_password.length())
                }
                else -> {
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login -> {
                val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_signup ->{
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.forgot_pass -> {
                val intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
        }
    }
}