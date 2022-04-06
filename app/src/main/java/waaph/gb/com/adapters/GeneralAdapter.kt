package waaph.gb.com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_general.view.*
import waaph.gb.com.R
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.model.CreateGeneralModel
import waaph.gb.com.model.Data

class GeneralAdapter(
    val context: Context,
    private val list: ArrayList<CreateGeneralModel>,
   /* private val listener: OnRecyclerViewItemClickListener<CreateGeneralModel>?*/) : RecyclerView.Adapter<GeneralAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GeneralAdapter.ItemViewHolder = ItemViewHolder(
        LayoutInflater.from(context).inflate(R.layout.rv_item_general, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }



    /*override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val information = list[position]
        holder.textView.text = information.company
        holder.itemView.setOnClickListener {
            listener?.onItemClick(it*//*, policy*//*, position)
        }
    }*/
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val information = list[position]
        holder.itemView.businessName.text = information.company
             /*   holder.itemView.setOnClickListener {
                    listener?.onItemClick(it, position)
                }*/
    }
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



}