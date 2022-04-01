package waaph.gb.com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import waaph.gb.com.R
import waaph.gb.com.model.CreateGeneralModel

class GeneralAdapter(
    val context: Context,
    private val list: ArrayList<CreateGeneralModel>,
    ) : RecyclerView.Adapter<GeneralAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_general, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.businessName)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val information = list[position]
        holder.textView.text = information.company
    }

}