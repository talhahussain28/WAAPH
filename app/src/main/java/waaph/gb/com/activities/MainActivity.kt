package waaph.gb.com.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import waaph.gb.com.R
import waaph.gb.com.database.user.UserDatabase
import waaph.gb.com.entities.user.UserEnt
import waaph.gb.com.network.ServiceUtils
import waaph.gb.com.responses.LoginRequest
import waaph.gb.com.responses.LoginResponse
import waaph.gb.com.utils.*

class MainActivity : BaseActivity(), View.OnClickListener, Callback<LoginResponse> {
    private var isVisiblePassword = false

    private var emailST = ""
    private var passwordST: String = ""
    lateinit var userDataBase: UserDatabase
    private var userEnt: UserEnt? = null
    private var prefs: SaveInSharedPreference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDataBase = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,
            "userDB"
        ).build()

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
//                valdateEdittext()
                emailST = email.text.toString().trim()
                passwordST = edit_text_password.text.toString()
                if (emailST.isNotEmpty()) {
                    if (internetConnectionAvailable(2000)) {
                        loginServiceApi(emailST, passwordST)
                    } else {
                        showAlertMessage("Internet Alert", "Please Check Internet connection")
                    }

                } else {
                    showAlertMessage("Alert", "Something went wrong")
                }

                /*val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
                finish()*/

            }
            R.id.tv_signup -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.forgot_pass -> {
                /*val intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)*/

                CoroutineScope(IO).launch {
                    userDataBase.userDao.addUser(UserEnt(0, "Talha", "talha@gmail.com", "123123"))
                }
            }
        }
    }


    private fun loginServiceApi(email: String, password: String) {
        showDialog("Please wait...")

        val param = LoginRequest(email, password)
        val call = ServiceUtils.createService().login(param)
        call.enqueue(this)
    }

    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>) {
        dismissDialog()
        if (response.isSuccessful) {
            val loginResponse: LoginResponse? = response.body()
            if (loginResponse?.Result == true) {
                prefs?.setString(Constants.ARG_TOKEN, loginResponse.Data)
                showToast(loginResponse.Data)
                //intentStartActivityWithFinish(this,BottomNavigationActivity::class.java)
            } else {
                showToast("loginResponse.message")
            }
        } else {
            // error case
            when (response.code()) {
                404 -> setLog("MainActivity", "not found")
                500 -> setLog("MainActivity", "server broken")
                else -> setLog("MainActivity", "unknown error")
            }

            setLog("MainActivity", response.errorBody().toString())
            showAlertMessage("Authenticate Error", "Please try again later")
        }

    }

    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
        dismissDialog()
        showAlertMessage("Authenticate Error", "Please try again later")
        setLog("MainActivity", t!!.localizedMessage)
    }

    private fun showAlertMessage(title: String, message: String) {
        DialogAlert(false,
            this,
            title, R.color.black,
            message, R.color.black,
            "", R.color.ThemeColor, R.color.black, false,
            "Cancel", R.color.ThemeColor, R.color.white, true,
            false, { dialog: Dialog ->
                dialog.dismiss()
                null
            }) { dialog: Dialog? -> null }.show()
    }


    private fun validateEdittext() {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        emailST = email.text.toString().trim()
        passwordST = edit_text_password.text.toString()

        if (emailST.isNotEmpty()) {
            if (emailST.matches(emailPattern.toRegex())) {
                if (passwordST == "0") {

                    /*CoroutineScope(IO).launch {
                        userEnt = userDataBase.userDao.loginUser(emailST, passwordST)
                    }*/
                    /*val intent = Intent(this, BottomNavigationActivity::class.java)
                    startActivity(intent)*/
                    CoroutineScope(Main).launch {
                        if (userEnt == null) {
                            showToast("Invalid Credentials!")
                        } else {
                            showToast("Login Successful!")
                        }
                    }

                } else {
                    edit_text_password.error = "Enter password!"
                }
            } else {
                email.error = "Invalid Email!"
            }
        } else {
            email.error = "Enter Email!"
        }
    }

    private fun valdateEdittext() {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        emailST = email.text.toString().trim()
        passwordST = edit_text_password.text.toString()
        if (emailST == "test@gmail.com" && passwordST == "123123" || emailST == "talha@gmail.com" && passwordST == "123123") {
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
            showToast("Success")
        } else {
            showToast("Invalid Credentials!")
        }
    }
}