package waaph.gb.com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_payment.view.*
import waaph.gb.com.R
import waaph.gb.com.model.CreatePaymentModel

class PaymentAdapter(
    val context: Context,
    private val list: ArrayList<CreatePaymentModel>, ) : RecyclerView.Adapter<PaymentAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentAdapter.ItemViewHolder = ItemViewHolder(
        LayoutInflater.from(context).inflate(R.layout.rv_item_payment, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val information = list[position]
        holder.itemView.paymentPersonName.text = information.name
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}