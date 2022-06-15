package waaph.gb.com

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.EditTextDrawableClick

class SignUpActivity : BaseActivity(),View.OnClickListener {
    private var isVisiblePassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setOnClickListener()
        initialize()

    }

    override fun linkXML() { }

    override fun setOnClickListener() {
        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener(this)
        login.setOnClickListener(this)
        edit_text_password.setOnClickListener(this)
        edit_text_confirm_password.setOnClickListener(this)
    }


    override fun initialize() {
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
        edit_text_confirm_password.setDrawableClickListener { target ->
            when (target) {
                EditTextDrawableClick.DrawableClickListener.DrawablePosition.LEFTT -> {
                }
                EditTextDrawableClick.DrawableClickListener.DrawablePosition.RIGHTR -> {
                    isVisiblePassword = !isVisiblePassword
                    if (isVisiblePassword) {
                        // Show
                        edit_text_confirm_password.transformationMethod = null
                    } else {
                        // Hide
                        edit_text_confirm_password.transformationMethod =
                            PasswordTransformationMethod()
                    }
                    edit_text_confirm_password.setSelection(edit_text_confirm_password.length())
                }
                else -> {
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {
                val intent = Intent(this, Signup1Activity::class.java)
                startActivity(intent)
            }
            R.id.login -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}