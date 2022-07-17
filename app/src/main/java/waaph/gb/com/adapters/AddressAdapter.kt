package waaph.gb.com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_address.view.*
import waaph.gb.com.R
import waaph.gb.com.entities.cdf.AddressEnt
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener

class AddressAdapter(
    val context: Context,
    private var list: ArrayList<AddressEnt>,
    private val listener: OnRecyclerViewItemClickListener<AddressEnt>?
) : RecyclerView.Adapter<AddressAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddressAdapter.ItemViewHolder = ItemViewHolder(
        LayoutInflater.from(context).inflate(R.layout.rv_item_address, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val information = list[position]

        holder.itemView.AddressName.text = information.addressName
        holder.itemView.street.text = information.addressLine

        holder.itemView.setOnClickListener {
            listener?.onItemClick(it, information,position)
        }

    }

    fun addItem(item: AddressEnt) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun updateList(newList: ArrayList<AddressEnt>){
        list = newList
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}