package waaph.gb.com.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.media.MediaScannerConnection
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.telephony.SmsManager
import android.text.InputFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.format.DateUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.util.TypedValue
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.*
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

class Utils {
    companion object {
        fun checkMultipleRunTimePermission(
            context: Context?,
            list: List<String>,
            requestCode: Int
        ): Boolean {
            val permissionsNeeded: MutableList<String> = ArrayList()
            for (permission in list) {
                if (ActivityCompat.checkSelfPermission(
                        context!!,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissionsNeeded.add(permission)
                }
            }
            if (!permissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(
                    (context as Activity?)!!,
                    permissionsNeeded.toTypedArray(), requestCode
                )
                return false
            }
            return true
        }


        fun etValidate(edittext: TextInputEditText): Boolean {
            var validate = edittext.text.toString()
            validate = validate.replace("\\s+".toRegex(), " ").trim { it <= ' ' }
            if (validate.isEmpty()) {
                edittext.error = "Required"
                return false
            }
            return true
        }

        fun etValidation(edittext: EditText): Boolean {
            var validate = edittext.text.toString()
            validate = validate.replace("\\s+".toRegex(), " ").trim { it <= ' ' }
            if (validate.isEmpty()) {
                edittext.error = "Required"
                return false
            }
            return true
        }

        fun isValidUrl(editText: EditText): Boolean {
            val url = editText.text.toString()
            val pattern = Patterns.WEB_URL
            val matcher = pattern.matcher(url)
            if (!matcher.matches()) {
                editText.error = "Invalid URL"
            }
            return matcher.matches()
        }

        fun isValidNumber(editText: EditText): Boolean {
            val phone = editText.text.toString()
            val pattern = Patterns.PHONE
            val matcher = pattern.matcher(phone)
            return if (matcher.matches()) matcher.matches() else {
                editText.error = "Invalid Email"
                matcher.matches()
            }
        }

        fun tvValidate(textView: TextView): Boolean {
            var validate = textView.text.toString()
            validate = validate.replace("\\s+".toRegex(), " ").trim { it <= ' ' }
            if (validate.isEmpty()) {
                textView.error = "Required"
                return false
            }
            return true
        }

        fun isValidEmail(edittext: EditText): Boolean {
            val email = edittext.text.toString()
            val EMAIL_PATTERN = ("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
            val pattern = Pattern.compile(EMAIL_PATTERN)
            val matcher = pattern.matcher(email)
            return if (matcher.matches()) matcher.matches() else {
                edittext.error = "Invalid Email"
                matcher.matches()
            }
        }

        fun matchPassword(password: EditText, confirm_password: EditText): Boolean {
            return if (password.text.toString() == confirm_password.text.toString()) {
                true
            } else {
                confirm_password.error = "Password did not match"
                false
            }
        }

        fun isValidPassword(edittext: EditText): Boolean {
            val password = edittext.text.toString()
            //        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
            val PASSWORD_PATTERN = "((?!\\s)\\A)(\\s|(?<!\\s)\\S){6,20}\\Z"
            val pattern = Pattern.compile(PASSWORD_PATTERN)
            val matcher = pattern.matcher(password)
            return if (matcher.matches()) matcher.matches() else {
                edittext.error = "At least 6 characters"
                matcher.matches()
            }
        }

    }
}
