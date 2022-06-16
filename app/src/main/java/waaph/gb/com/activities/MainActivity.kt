package waaph.gb.com.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.edit_text_password
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import waaph.gb.com.R
import waaph.gb.com.database.UserDatabase
import waaph.gb.com.entities.user.UserEnt
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.EditTextDrawableClick

class MainActivity : BaseActivity(), View.OnClickListener {
    private var isVisiblePassword = false

    lateinit var userDataBase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDataBase = Room.databaseBuilder(applicationContext, UserDatabase::class.java,
        "userDB").build()




        setOnClickListener()
        initialize()
    }

    override fun linkXML() {

    }

    override fun setOnClickListener() {
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener(this)
        tv_signup.setOnClickListener(this)
        forgot_pass.setOnClickListener(this)
        edit_text_password.setOnClickListener(this)
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
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login -> {
                val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.tv_signup ->{
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.forgot_pass -> {
                /*val intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)*/

                GlobalScope.launch {
                    userDataBase.userDao.addUser(UserEnt(0, "Talha", "talha@gmail.com", "123123"))
                }
            }
        }
    }
}