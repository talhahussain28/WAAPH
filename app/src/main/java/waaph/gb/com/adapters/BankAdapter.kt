package waaph.gb.com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item_bank.view.*
import waaph.gb.com.R
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.model.Data

class BankAdapter(private val context: Context,
                  private val list: ArrayList<Data>,
                  private val listener: OnRecyclerViewItemClickListener<Data>?):
    RecyclerView.Adapter<BankAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BankAdapter.ItemViewHolder = ItemViewHolder(
        LayoutInflater.from(context).inflate(R.layout.row_item_bank, parent, false))

    override fun onBindViewHolder(holder: BankAdapter.ItemViewHolder, position: Int) {
        bindItemViewHolder(holder, position)
    }

    private fun bindItemViewHolder(holder: ItemViewHolder, position: Int) {
        val policy = list[position]
        holder.itemView.title.text = policy.title
        holder.itemView.bank.text = policy.babnk

        holder.itemView.setOnClickListener {
            listener?.onItemClick(it/*, policy*/, position)
        }
    }


    override fun getItemCount(): Int = list.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}