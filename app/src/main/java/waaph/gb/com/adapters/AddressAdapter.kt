package waaph.gb.com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_address.view.*
import kotlinx.android.synthetic.main.rv_item_contact.view.*
import waaph.gb.com.R
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.model.CreateAddressModel
import waaph.gb.com.model.Data

class AddressAdapter(
    val context: Context,
    private val list: ArrayList<CreateAddressModel>,
    private val listener: OnRecyclerViewItemClickListener<Data>?
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
        holder.itemView.street.text = information.address

        holder.itemView.setOnClickListener {
            listener?.onItemClick(it, position)
        }

    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}