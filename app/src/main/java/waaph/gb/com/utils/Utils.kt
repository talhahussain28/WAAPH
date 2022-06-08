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
    fun removeLastChar(str: String): String {
        return str.substring(0, str.length - 1)
    }

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

        fun checkMobileVersionForPermisson(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        }

        fun buildAlertMessageNoGps(context: Context, REQUEST_LOCATION: Int) {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id -> //                        context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    (context as Activity).startActivityForResult(
                        Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                        REQUEST_LOCATION
                    )
                }
                .setNegativeButton(
                    "No"
                ) { dialog, id -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        }

        val screenWidth: Int
            get() = Resources.getSystem().displayMetrics.widthPixels
        val screenHeight: Int
            get() = Resources.getSystem().displayMetrics.heightPixels
        val randomColor: Int
            get() {
                val rnd = Random()
                return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            }

        fun enableTypeEditText(editText: EditText, type: Boolean) {
            editText.isEnabled = type
            editText.isFocusable = type
            //        if (type)
//            editText.setInputType(InputType.TYPE_CLASS_TEXT);
//        else
//            editText.setInputType(InputType.TYPE_NULL);
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

        // ignore enter First space on edittext
        fun ignoreFirstWhiteSpace(): InputFilter {
            return InputFilter { source, start, end, dest, dstart, dend ->
                if (dest.length == 0) {
                    for (i in start until end) {
                        if (Character.isWhitespace(source[i])) {
                            if (i == 0) return@InputFilter ""
                        }
                    }
                }
                null
            }
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

        fun getRandomNumber(min: Int, max: Int): Int {
            return Random().nextInt(max - min + 1) + min
        }

        fun passwordLength(password: EditText, length: Int): Boolean {
            return if (password.text.length >= length) {
                true
            } else {
                password.error = "At least 6 characters"
                false
            }
        }

        fun validTime(previousTime: Long, newTime: Long): Boolean {
            var isValid = false
            if (newTime > previousTime) {
                isValid = true
            }
            return isValid
        }

        fun showTitleValidation(editText: EditText): Boolean {
            val title = editText.text.toString()
            val PATTERN = "^[a-zA-Z ]+$"
            val pattern = Pattern.compile(PATTERN)
            val matcher = pattern.matcher(title)
            return if (matcher.matches()) matcher.matches() else {
                editText.error = "Only charachers allow"
                matcher.matches()
            }
        }

        fun getFormatedAmount(amount: Float): String {
            val numberFormat = NumberFormat.getCurrencyInstance()
            val decimalFormatSymbols = (numberFormat as DecimalFormat).decimalFormatSymbols
            decimalFormatSymbols.currencySymbol = ""
            numberFormat.decimalFormatSymbols = decimalFormatSymbols
            return numberFormat.format(amount.toDouble())
        }

        /**
         * Return date in specified format.
         * @param seconds Date in seconds
         * @param is_AM_PM Date 12 hours and 24 hours format
         * @return String representing date in specified format
         */
        fun getTimeFormat(seconds: Long, is_AM_PM: Boolean): String {
            val startTimeHours = TimeUnit.SECONDS.toHours(seconds)
            val startTimeMinutes = TimeUnit.SECONDS.toMinutes(seconds) % 60
            return if (is_AM_PM) {
                if (startTimeHours > 12) {
                    String.format("%02d:%02d %2s", startTimeHours - 12, startTimeMinutes, "PM")
                } else {
                    String.format(
                        "%02d:%02d %2s",
                        startTimeHours,
                        startTimeMinutes,
                        if (startTimeHours > 11) "PM" else "AM"
                    )
                }
            } else String.format("%02d:%02d", startTimeHours, startTimeMinutes)
        }

        @SuppressLint("SimpleDateFormat")
        fun getDateFullSpell(dateTime: String): String {

            // 2017-03-20 13:45:00
            val data = dateTime.split(" ").toTypedArray()
            val date = data[0]

//        String input_date="24-03-2017";
            val format1 = SimpleDateFormat("yyyy-MM-dd")
            var dt1: Date? = null
            try {
                dt1 = format1.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val formatDate: DateFormat = SimpleDateFormat("dd")
            val formatDay: DateFormat = SimpleDateFormat("EEEE")
            val formatMonth: DateFormat = SimpleDateFormat("MMMM")
            val formatYear: DateFormat = SimpleDateFormat("yyyy")
            val finalDay = formatDay.format(dt1)
            val finalDate = formatDate.format(dt1)
            val finalMonth = formatMonth.format(dt1)
            val finalYear = formatYear.format(dt1)
            //        switch (finalDate){
//            case "01":
//                finalDate = finalDate + "st";
//                break;
//            case "02":
//                finalDate = finalDate + "nd";
//                break;
//            case "03":
//                finalDate = finalDate + "rd";
//                break;
//            default:
//                finalDate = finalDate + "th";
//                break;
//        }
            return "$finalDay $finalDate $finalMonth $finalYear"
        }

        @SuppressLint("SimpleDateFormat")
        fun getDate(dateTime: String): String {

            // 2017-03-20 13:45:00
            val data = dateTime.split(" ").toTypedArray()
            val date = data[0]

//        String input_date="24-03-2017";
            val format1 = SimpleDateFormat("yyyy-MM-dd")
            var dt1: Date? = null
            try {
                dt1 = format1.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val formatDate: DateFormat = SimpleDateFormat("dd")
            val formatDay: DateFormat = SimpleDateFormat("EEE")
            val formatMonth: DateFormat = SimpleDateFormat("MMM")
            val formatYear: DateFormat = SimpleDateFormat("yyyy")
            val finalDay = formatDay.format(dt1)
            val finalDate = formatDate.format(dt1)
            val finalMonth = formatMonth.format(dt1)
            val finalYear = formatYear.format(dt1)
            return "$finalDay $finalDate $finalMonth $finalYear"
        }

        fun getTime(dateTime: String): String {

            // 2017-03-20 13:45:00
            val data = dateTime.split(" ").toTypedArray()
            var time = data[1]
            val militeryTime = StringBuilder()
            val date = time.split(":").toTypedArray()
            for (i in 0..1) {
                militeryTime.append(date[i])
            }
            if (militeryTime == null) return time

            // Convert time where time is like: 0100, 0200, 0300....2300...
            var hour = militeryTime.substring(0, 2)
            val minutes = militeryTime.substring(2, 4)
            var meridian = "AM"
            if (hour.substring(0, 2) == "00") {
                hour = "12"
            } else if (Integer.valueOf(hour) > 11) {
                meridian = "PM"
                val militaryHour = hour.toInt()
                val convertedHour: Int
                if (militaryHour > 12) {
                    convertedHour = militaryHour - 12
                    hour = if (convertedHour < 10) "0$convertedHour" else convertedHour.toString()
                }
            }

//        if (hour.substring(0,2).equals("00")) {
//            hour = "12";
//        } else if (hour.substring(0,1).equals("1") || hour.substring(0,1).equals("2")) {
//            meridian = "PM";
//            Integer militaryHour = Integer.parseInt(hour);
//            Integer convertedHour = null;
//
//            if (militaryHour > 12) {
//                convertedHour = (militaryHour - 12);
//
//                if (convertedHour < 10)
//                    hour = "0" + String.valueOf(convertedHour);
//                else
//                    hour = String.valueOf(convertedHour);
//            }
//        }
            time = "$hour:$minutes $meridian"


            // TODO - Convert time where time is like 01:00...23:00...
            return time
        }

      /*  fun timeConvert(minutes: Int): String {
            return if (minutes > 0) {
                val days = minutes / 60 / 24
                if (days > 0) {
                    "$days days remaining"
                } else {
                    val hours = minutes / 60
                    if (hours > 0) {
                        "$hours hours remaining"
                    } else {
                        "$minutes minutes remaining"
                    }
                }
            } else {
                minutes / 24 / (60.toString()) + " days past"
            }
        }*/

        fun getRelativeTimeSpan(date: Long, simpleDateFormat: SimpleDateFormat?): String {
            val diffDays = getDiffBetweenDates(System.currentTimeMillis(), date)
            return if (diffDays["Sec"]!! < 121) {
                "Just Now"
            } else if (diffDays["Min"]!! < 60) {
                DateUtils.getRelativeTimeSpanString(
                    date,
                    System.currentTimeMillis(),
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_TIME
                ).toString()
            } else if (diffDays["Hour"]!! < 24) {
                DateUtils.getRelativeTimeSpanString(
                    date,
                    System.currentTimeMillis(),
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_WEEKDAY
                ).toString()
            } else if (diffDays["Day"]!! < 7) {
                DateUtils.getRelativeTimeSpanString(
                    date,
                    System.currentTimeMillis(),
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_MONTH
                ).toString()
            } else if (simpleDateFormat != null) {
                simpleDateFormat.format(Date(date))
            } else {
                ""
            }
        }

        fun getDiffBetweenDates(date1: Long, date2: Long): HashMap<String, Long> {
            val diffInMillisec = date1 - date2
            val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec)
            val diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillisec)
            val diffInMin = TimeUnit.MILLISECONDS.toMinutes(diffInMillisec)
            val diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec)
            val hashMap = HashMap<String, Long>()
            hashMap["Day"] = diffInDays
            hashMap["Hour"] = diffInHours
            hashMap["Min"] = diffInMin
            hashMap["Sec"] = diffInSec
            return hashMap
        }

        fun getMimeType(context: Context, uri: Uri): String? {
            val extension: String?

            //Check uri format to avoid null
            extension = if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
                //If scheme is a content
                val mime = MimeTypeMap.getSingleton()
                mime.getExtensionFromMimeType(context.contentResolver.getType(uri))
            } else {
                //If scheme is a File
                //This will replace white spaces with %20 and also other special characters.
                // This will avoid returning null values on file name with spaces and special characters.
                MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(File(uri.path)).toString())
            }
            return extension
        }

        fun getMimeType(url: String?): String? {
            var type: String? = ""
            val extension = MimeTypeMap.getFileExtensionFromUrl(url)
            if (extension != null) {
                type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            }
            return type
        }

        fun dateFromUTC(date: Date): Date {
            return Date(date.time + Calendar.getInstance().timeZone.getOffset(date.time))
        }

        fun dateToUTC(date: Date): Date {
            return Date(date.time - Calendar.getInstance().timeZone.getOffset(date.time))
        }

        fun createBirthYearList(): List<String> {
            val birthYear: MutableList<String> = ArrayList()
            for (i in 71 downTo 1) {
                birthYear.add((1939 + i).toString())
            }
            return birthYear
        }

        fun setWordPlural(count: Int, word: String): String {
            if (count <= 0) {
                return "0 $word"
            } else if (count == 1) {
                return "$count $word"
            }
            return count.toString() + " " + word + "s"
        }

        fun dp2px(resource: Resources, dp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                resource.displayMetrics
            )
                .toInt()
        }

        fun px2dp(resource: Resources, px: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                px.toFloat(),
                resource.displayMetrics
            )
                .toInt()
        }

        fun spanTextView(context: Context?, textView: TextView) {

//        final TextView textView = view.findViewById(R.id.textView);
            textView.text = "Please read Normal Link and NoUnderLine Link and Highlight Link"
            textView.setLinkTextColor(Color.BLUE) // default link color for clickable span, we can also set it in xml by android:textColorLink=""
            val normalLinkClickSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    Toast.makeText(context, "Normal Link", Toast.LENGTH_SHORT).show()
                }
            }
            val noUnderLineClickSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    Toast.makeText(context, "NoUnderLine Link", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                    ds.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    ds.color = Color.MAGENTA // specific color for this link
                }
            }
            val highlightClickSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    Toast.makeText(context, "Highlight Link", Toast.LENGTH_SHORT)
                        .show()
                    view.invalidate() // need put invalidate here to make text change to GREEN after clicked
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = false
                    if (textView.isPressed && textView.selectionStart != -1 && (textView.text
                            .toString()
                            .substring(textView.selectionStart, textView.selectionEnd)
                                == "Highlight Link")
                    ) {
                        ds.color =
                            Color.RED // need put invalidate here to make text change to RED when pressed on Highlight Link                    textView.invalidate();
                    } else {
                        ds.color = Color.GREEN
                    }
                    // dont put invalidate here because if you put invalidate here `updateDrawState` will called forever
                }
            }
            makeLinks(
                textView, arrayOf(
                    "Normal Link", "NoUnderLine Link", "Highlight Link"
                ), arrayOf(
                    normalLinkClickSpan, noUnderLineClickSpan, highlightClickSpan
                )
            )
        }

        fun makeLinks(
            textView: TextView,
            links: Array<String>,
            clickableSpans: Array<ClickableSpan>
        ) {
            val spannableString = SpannableString(textView.text)
            for (i in links.indices) {
                val clickableSpan = clickableSpans[i]
                val link = links[i]
                val startIndexOfLink = textView.text.toString().indexOf(link)
                try {
                    spannableString.setSpan(
                        clickableSpan, startIndexOfLink,
                        startIndexOfLink + link.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } catch (ex: IndexOutOfBoundsException) {
                    Log.d("spannableStr-exception", ex.localizedMessage)
                }
            }
            textView.highlightColor =
                Color.TRANSPARENT // prevent TextView change background when highlight
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.setText(spannableString, TextView.BufferType.SPANNABLE)
        }

        /*fun makeLinks(textView: TextView, mentions: List<MentionLinkModel?>) {
            val spannableString = SpannableString(textView.text)
            for (link in mentions) {
                val p = Pattern.compile(link.component1())
                val m = p.matcher(textView.text.toString())
                var spanIndex = 0
                while (m.find()) {
                    try {
                        spannableString.setSpan(
                            link.getClickableSpan().get(spanIndex), m.start(),
                            m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    } catch (ex: IndexOutOfBoundsException) {
                        Log.d("spannableStr-exception", ex.localizedMessage)
                    }
                    spanIndex++
                }
            }
            textView.highlightColor =
                Color.TRANSPARENT // prevent TextView change background when highlight
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.setText(spannableString, TextView.BufferType.SPANNABLE)
        }
*/
        fun makeLinksEditText(
            editText: EditText,
            links: Array<String>,
            clickableSpans: Array<ClickableSpan?>
        ) {
            val spannableString = SpannableString(editText.text)
            for (i in links.indices) {
                val clickableSpan = clickableSpans[i]
                val link = links[i]
                val startIndexOfLink = editText.text.toString().indexOf(link)
                try {
                    spannableString.setSpan(
                        clickableSpan, startIndexOfLink,
                        startIndexOfLink + link.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } catch (ex: IndexOutOfBoundsException) {
                    Log.d("spannableStr-exception", ex.localizedMessage)
                }
            }
            editText.highlightColor =
                Color.TRANSPARENT // prevent TextView change background when highlight
            editText.movementMethod = LinkMovementMethod.getInstance()
            editText.setText(spannableString, TextView.BufferType.SPANNABLE)
        }

      /*  fun bitmapDescriptorFromVector(
            context: Context?,
            @DrawableRes vectorDrawableResourceId: Int
        ): BitmapDescriptor {
            val background = ContextCompat.getDrawable(context!!, vectorDrawableResourceId)
            background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
            //        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
//        vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
            val bitmap = Bitmap.createBitmap(
                background.intrinsicWidth,
                background.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            background.draw(canvas)
            //        vectorDrawable.draw(canvas);
            return BitmapDescriptorFactory.fromBitmap(bitmap)
        }*/

        fun getFileExtension(name: String): String {
            return name.substring(name.lastIndexOf("."))
        }

        fun openFile(context: Context, url: File) {
            try {
                val uri = Uri.fromFile(url)
                val intent = Intent(Intent.ACTION_VIEW)
                if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                    // Word document
                    intent.setDataAndType(uri, "application/msword")
                } else if (url.toString().contains(".pdf")) {
                    // PDF file
                    intent.setDataAndType(uri, "application/pdf")
                } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                    // Powerpoint file
                    intent.setDataAndType(uri, "application/vnd.ms-powerpoint")
                } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                    // Excel file
                    intent.setDataAndType(uri, "application/vnd.ms-excel")
                } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
                    // WAV audio file
                    intent.setDataAndType(uri, "application/x-wav")
                } else if (url.toString().contains(".rtf")) {
                    // RTF file
                    intent.setDataAndType(uri, "application/rtf")
                } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                    // WAV audio file
                    intent.setDataAndType(uri, "audio/x-wav")
                } else if (url.toString().contains(".gif")) {
                    // GIF file
                    intent.setDataAndType(uri, "image/gif")
                } else if (url.toString().contains(".jpg") || url.toString()
                        .contains(".jpeg") || url.toString().contains(".png")
                ) {
                    // JPG file
                    intent.setDataAndType(uri, "image/jpeg")
                } else if (url.toString().contains(".txt")) {
                    // Text file
                    intent.setDataAndType(uri, "text/plain")
                } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") ||
                    url.toString().contains(".mpeg") || url.toString()
                        .contains(".mpe") || url.toString().contains(".mp4") || url.toString()
                        .contains(".avi")
                ) {
                    // Video files
                    intent.setDataAndType(uri, "video/*")
                } else {
                    intent.setDataAndType(uri, "*/*")
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "No application found which can open the file",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        fun setDrawableTint(context: Context?, drawable: Drawable?, color: Int): Drawable {
            val newDrawable = DrawableCompat.wrap(drawable!!)
            DrawableCompat.setTint(newDrawable, color)
            return newDrawable
        }

        fun addWatermark(res: Resources?, source: Bitmap): Bitmap {
            val w: Int
            val h: Int
            val c: Canvas
            val paint: Paint
            val bmp: Bitmap
            val watermark: Bitmap? = null
            val matrix: Matrix
            var scale: Float
            val r: RectF
            w = source.width
            h = source.height
            bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG)

//        watermark = BitmapFactory.decodeResource(res, R.mipmap.ic_water_mark);
//        scale = (float) (((float) h * 0.10) / (float) watermark.getHeight());
            c = Canvas(bmp)
            c.drawBitmap(source, 0f, 0f, paint)
            matrix = Matrix()
            matrix.postScale(3.0.toFloat(), 3.0.toFloat())
            r = RectF(0F, 0F, watermark!!.width.toFloat(), watermark.height.toFloat())
            matrix.mapRect(r)

//        matrix.postTranslate(w/2, h/2);
            val centerImageW = (w / 2).toFloat()
            val centerImageH = (h / 2).toFloat()
            val centerMarkW = (watermark.width * 3 / 2).toFloat()
            val centerMarkH = (watermark.height * 3 / 2).toFloat()
            val centerW = centerImageW - centerMarkW
            val centerH = centerImageH - centerMarkH
            matrix.postTranslate(centerW, centerH)
            c.drawBitmap(watermark, matrix, paint)
            watermark.recycle()
            return bmp
        }

        fun compressImage(context: Context, imageUri: String): String {
            val filePath = getRealPathFromURI(context, imageUri)
            var scaledBitmap: Bitmap? = null
            val options = BitmapFactory.Options()

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
            options.inJustDecodeBounds = true
            var bmp = BitmapFactory.decodeFile(filePath, options)
            var actualHeight = options.outHeight
            var actualWidth = options.outWidth

//      max Height and width values of the compressed image is taken as 816x612
            val maxHeight = 1920.0f
            val maxWidth = 1080.0f
            var imgRatio = (actualWidth / actualHeight).toFloat()
            val maxRatio = maxWidth / maxHeight

//      width and height values are set maintaining the aspect ratio of the image
            if (actualHeight > maxHeight || actualWidth > maxWidth) {
                if (imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight
                    actualWidth = (imgRatio * actualWidth).toInt()
                    actualHeight = maxHeight.toInt()
                } else if (imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth
                    actualHeight = (imgRatio * actualHeight).toInt()
                    actualWidth = maxWidth.toInt()
                } else {
                    actualHeight = maxHeight.toInt()
                    actualWidth = maxWidth.toInt()
                }
            }

//      setting inSampleSize value allows to load a scaled down version of the original image
            options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)

//      inJustDecodeBounds set to false to load the actual bitmap
            options.inJustDecodeBounds = false

//      this options allow android to claim the bitmap memory if it runs low on memory
            options.inPurgeable = true
            options.inInputShareable = true
            options.inTempStorage = ByteArray(16 * 1024)
            try {
//          load the bitmap from its path
                bmp = BitmapFactory.decodeFile(filePath, options)
            } catch (exception: OutOfMemoryError) {
                exception.printStackTrace()
            }
            try {
                scaledBitmap =
                    Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
            } catch (exception: OutOfMemoryError) {
                exception.printStackTrace()
            }
            val ratioX = actualWidth / options.outWidth.toFloat()
            val ratioY = actualHeight / options.outHeight.toFloat()
            val middleX = actualWidth / 2.0f
            val middleY = actualHeight / 2.0f
            val scaleMatrix = Matrix()
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)
            val canvas = Canvas(scaledBitmap!!)
            canvas.setMatrix(scaleMatrix)
            canvas.drawBitmap(
                bmp,
                middleX - bmp.width / 2,
                middleY - bmp.height / 2,
                Paint(Paint.FILTER_BITMAP_FLAG)
            )

//      check the rotation of the image and display it properly
            val exif: ExifInterface
            try {
                exif = ExifInterface(filePath!!)
                val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0
                )
                Log.d("EXIF", "Exif: $orientation")
                val matrix = Matrix()
                if (orientation == 6) {
                    matrix.postRotate(90f)
                    Log.d("EXIF", "Exif: $orientation")
                } else if (orientation == 3) {
                    matrix.postRotate(180f)
                    Log.d("EXIF", "Exif: $orientation")
                } else if (orientation == 8) {
                    matrix.postRotate(270f)
                    Log.d("EXIF", "Exif: $orientation")
                }
                scaledBitmap = Bitmap.createBitmap(
                    scaledBitmap, 0, 0,
                    scaledBitmap.width, scaledBitmap.height, matrix,
                    true
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val out: FileOutputStream
            val filename = filename
            try {
                out = FileOutputStream(filename)

//          write the compressed bitmap at the destination specified by filename.
                scaledBitmap!!.compress(Bitmap.CompressFormat.JPEG, 30, out)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            return filename
        }

        private fun getRealPathFromURI(context: Context, contentURI: String): String? {
            val contentUri = Uri.parse(contentURI)
            val cursor = context.contentResolver.query(contentUri, null, null, null, null)
            return if (cursor == null) {
                contentUri.path
            } else {
                cursor.moveToFirst()
                val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                cursor.close()
                cursor.getString(index)
            }
        }

        private val filename: String
            private get() {
                val file = File(Environment.getExternalStorageDirectory().path, "PWW/Images")
                if (!file.exists()) {
                    file.mkdirs()
                }
                return file.absolutePath + "/" + System.currentTimeMillis() + ".jpg"
            }

        fun getPath(context: Context, uri: Uri?): String? {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = context.contentResolver.query(uri!!, projection, null, null, null)
                ?: return null
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            val s = cursor.getString(column_index)
            cursor.close()
            return s
        }

        fun getThumbnailVideoUri(context: Context, videoUri: Uri?): Bitmap? {
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = context.contentResolver.query(videoUri!!, filePathColumn, null, null, null)
            cursor!!.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val videoThumbnail = cursor.getString(columnIndex)
            cursor.close()
            return ThumbnailUtils.createVideoThumbnail(
                videoThumbnail,
                MediaStore.Video.Thumbnails.FULL_SCREEN_KIND
            )
        }

        fun saveImage(context: Context?, finalBitmap: Bitmap): File {
            val root = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ).toString()
            val myDir = File("$root/thumbnail")
            myDir.mkdirs()
            val generator = Random()
            val fname = "Image-" + System.currentTimeMillis() + ".jpg"
            val file = File(myDir, fname)
            if (file.exists()) file.delete()
            try {
                val out = FileOutputStream(file)
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                // sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                //     Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            // Tell the media scanner about the new file so that it is
// immediately available to the user.
            MediaScannerConnection.scanFile(
                context, arrayOf(file.toString()), null
            ) { path, uri ->
                Log.i("ExternalStorage", "Scanned $path:")
                Log.i("ExternalStorage", "-> uri=$uri")
            }
            return file
        }

        private fun saveImagePrivately(context: Context, b: Bitmap, imageName: String) {
            val foStream: FileOutputStream
            try {
                foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE)
                b.compress(Bitmap.CompressFormat.PNG, 100, foStream)
                foStream.close()
            } catch (e: Exception) {
                Log.d("saveImage", "Exception 2, Something went wrong!")
                e.printStackTrace()
            }
        }

        fun sendMessage(context: Context?, number: String?, message: String?) {
            SmsManager.getDefault().sendTextMessage(number, null, message, null, null)
            //        Uri uri = Uri.parse("smsto:" + number);
//        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//        intent.putExtra("sms_body", message);
//        context.startActivity(intent);
        }

        fun nativeTextShareIntent(context: Context, message: String?) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, message)
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }

        private fun calculateInSampleSize(
            options: BitmapFactory.Options,
            reqWidth: Int,
            reqHeight: Int
        ): Int {
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1
            if (height > reqHeight || width > reqWidth) {
                val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
                val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
                inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
            }
            val totalPixels = (width * height).toFloat()
            val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()
            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++
            }
            return inSampleSize
        }

        fun getKeyHash(activity: Activity) {
            try {
                val info = activity.packageManager.getPackageInfo(
                    activity.packageName,
                    PackageManager.GET_SIGNATURES
                )
                for (signature in info.signatures) {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
                    Log.d("PackageName:", activity.packageName)
                }
            } catch (e1: PackageManager.NameNotFoundException) {
                Log.e("name not found", e1.toString())
            } catch (e: NoSuchAlgorithmException) {
                Log.e("no such an algorithm", e.toString())
            } catch (e: Exception) {
                Log.e("exception", e.toString())
            }
        }
    }
}
