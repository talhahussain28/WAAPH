package waaph.gb.com.utils

import android.app.Activity
import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import kotlinx.android.synthetic.main.custom_dialog_alert.*
import kotlinx.android.synthetic.main.custom_dialog_alert.view.*
import waaph.gb.com.R

class DialogAlert(
    setCancelable: Boolean = true,
    context: Activity,
    title : String = "",
    @ColorRes titleColor : Int = R.color.black,
    message: String = "",
    @ColorRes messageColor : Int = R.color.black,
    buttonLeft: String = "",
    @ColorRes buttonLeftTintColor : Int = R.color.ThemeColor,
    @ColorRes buttonLeftTextColor: Int = R.color.black,
    visibleLeftButton: Boolean = true,
    buttonRight: String = "",
    @ColorRes buttonRightTintColor : Int = R.color.ThemeColor,
    @ColorRes buttonRightTextColor: Int = R.color.white,
    visibleRightButton: Boolean = true,
    textAllCap: Boolean = false,
    onRightButtonClickListener: (Dialog) -> Unit,
    onLeftButtonClickListener: (Dialog) -> Unit
) : Dialog(context) {

    init {
        setContentView(R.layout.custom_dialog_alert)
        this.setCancelable(setCancelable)

        val display = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(display)
        window?.setGravity(Gravity.CENTER)
        window?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context,android.R.color.transparent)))
        window?.attributes?.windowAnimations = android.R.anim.fade_out
        dialogAlert.layoutParams.width = (display.widthPixels / 100) * 90
        dialogAlert.layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
//        dialogAlert.layoutParams.height = (display.heightPixels / 100) * 18

        dialogAlert.textView_title.text = title
        dialogAlert.textView_title.setTextColor(ContextCompat.getColor(context, titleColor))
        dialogAlert.textView_message.text = message
        dialogAlert.textView_message.setTextColor(ContextCompat.getColor(context, messageColor))

        dialogAlert.button_left.text = buttonLeft
        dialogAlert.button_left.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, buttonLeftTintColor))
        dialogAlert.button_left.setTextColor(ContextCompat.getColor(context, buttonLeftTextColor))
        dialogAlert.button_left.isAllCaps = textAllCap
        dialogAlert.button_left.visibility = if (visibleLeftButton) View.VISIBLE else View.GONE

        dialogAlert.button_right.text = buttonRight
        dialogAlert.button_right.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, buttonRightTintColor))
        dialogAlert.button_right.setTextColor(ContextCompat.getColor(context, buttonRightTextColor))
        dialogAlert.button_right.isAllCaps = textAllCap
        dialogAlert.button_right.visibility = if (visibleRightButton) View.VISIBLE else View.GONE

        dialogAlert.button_left.setOnClickListener { onLeftButtonClickListener.invoke(this) }
        dialogAlert.button_right.setOnClickListener { onRightButtonClickListener.invoke(this) }
    }
}