
package waaph.gb.com.utils

import android.view.View
import android.widget.EditText

fun EditText.getTextToString(): String{
    return this.text.toString().trim()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.isVisible(): Boolean = (this.visibility == View.VISIBLE)
