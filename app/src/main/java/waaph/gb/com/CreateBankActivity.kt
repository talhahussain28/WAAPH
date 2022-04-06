package waaph.gb.com

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_create_bank.*
import kotlinx.android.synthetic.main.custom_dialog.*
import waaph.gb.com.utils.GeneralBottomAdapter

class CreateBankActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_bank)

         bank.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bank -> {
                pickerActionDialog()
            }
        }
    }

     private fun pickerActionDialog() {

         val dialog = Dialog(this)
         dialog.setContentView(R.layout.custom_dialog)
         dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation_2
         dialog.window!!.setGravity(Gravity.BOTTOM)
         dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
         dialog.window!!.setLayout(
             WindowManager.LayoutParams.MATCH_PARENT,
             WindowManager.LayoutParams.WRAP_CONTENT
         )

         dialog.setCancelable(true)
         dialog.setCanceledOnTouchOutside(true)

         val listView = dialog.findViewById<ListView>(R.id.listView)
         val list: MutableList<GeneralBottomAdapter.ListItemModel> = ArrayList()

         list.add(GeneralBottomAdapter.ListItemModel("Meezan Bank"))
         list.add(GeneralBottomAdapter.ListItemModel("Option 2"))
         list.add(GeneralBottomAdapter.ListItemModel("Option 3"))
         list.add(GeneralBottomAdapter.ListItemModel("Option 4"))

         val adapter = GeneralBottomAdapter(this, "", list)
         listView.adapter = adapter
         dialog.listView.onItemClickListener =
             AdapterView.OnItemClickListener { parent, view, position, id ->
                 val data = parent.getItemAtPosition(position)
                         as GeneralBottomAdapter.ListItemModel

                 when (position) {
                     0 -> {
                         bank.text = "Meezan Bank"
                     }
                     1 -> {
                         bank.text = "Option 2"
                     }
                     2 -> {
                         bank.text = "Option 3"
                     }
                     3 -> {
                         bank.text = "Option 4"
                     }
                 }
                 dialog.dismiss()

             }
         dialog.show()

     }
}