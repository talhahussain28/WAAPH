package waaph.gb.com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_contact.view.*
import waaph.gb.com.R
import waaph.gb.com.entities.cdf.ContactEnt
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener

class ContactAdapter(
    val context: Context,
    private var list: ArrayList<ContactEnt>,
    private val listener: OnRecyclerViewItemClickListener<ContactEnt>?
) : RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.ItemViewHolder = ItemViewHolder(
        LayoutInflater.from(context).inflate(R.layout.rv_item_contact, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val information = list[position]
        holder.itemView.contactName.text = information.personName
        holder.itemView.contactNumber.text = information.designation

        holder.itemView.setOnClickListener {
            listener?.onItemClick(it, information,position)
        }

    }

    fun addItem(item: ContactEnt) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun updateList(newList: ArrayList<ContactEnt>){
        list = newList
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}