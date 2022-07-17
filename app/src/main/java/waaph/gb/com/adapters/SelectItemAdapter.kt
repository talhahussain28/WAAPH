package waaph.gb.com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_address.view.street
import kotlinx.android.synthetic.main.rvitem_selected_item.view.*
import waaph.gb.com.R
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.model.Data
import waaph.gb.com.model.SelectItemModel

class SelectItemAdapter(
    val context: Context,
    private val list: ArrayList<SelectItemModel>,
    private val listener: OnRecyclerViewItemClickListener<SelectItemModel>?
) : RecyclerView.Adapter<SelectItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectItemAdapter.ItemViewHolder = ItemViewHolder(
        LayoutInflater.from(context).inflate(R.layout.rvitem_selected_item, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val information = list[position]
        holder.itemView.tvShowItemName.text = information.itemName

        holder.itemView.setOnClickListener {
            listener?.onItemClick(it, information,position)
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}