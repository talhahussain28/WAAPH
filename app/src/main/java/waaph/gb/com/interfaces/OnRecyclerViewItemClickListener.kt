package waaph.gb.com.interfaces

import android.view.View

interface OnRecyclerViewItemClickListener<T> {
    fun onItemClick(itemView: View?/*, data: T?*/, position: Int)
}