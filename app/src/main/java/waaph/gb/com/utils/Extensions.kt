
package waaph.gb.com.utils

import android.graphics.Bitmap
import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.view.View
import com.google.gson.GsonBuilder
import java.lang.reflect.Type
import kotlin.math.round

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
