package waaph.gb.com.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import android.widget.ListView
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_bank.*
import kotlinx.android.synthetic.main.fragment_bank.next
import kotlinx.android.synthetic.main.fragment_general.*
import waaph.gb.com.CustomerDataFormActivity
import waaph.gb.com.R
import waaph.gb.com.utils.GeneralBottomAdapter
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BankFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next.setOnClickListener(this)
        bank_layout.setOnClickListener(this)
    }

    companion object fun newInstance(){

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {
                (activity as CustomerDataFormActivity).setCurrentItem(4)

                // (activity as CustomerDataFormActivity).ViewPagerAdapter(parentFragmentManager).setCurrentItem(4)

              /*  val intent = Intent(requireActivity(), PaymentFragment::class.java)
                startActivity(intent)*/
            }
            R.id.bank_layout ->{
                pickerActionDialog()
            }

        }
    }
    private fun pickerActionDialog() {

        val dialog = Dialog(requireContext())
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

        val adapter = GeneralBottomAdapter(requireContext(), "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        Bank.setText("Meezan Bank")
                    }
                    1 -> {
                        Bank.setText("Option 2")
                    }
                    2 -> {
                        Bank.setText("Option 3")
                    }
                    3 -> {
                        Bank.setText("Option 4")
                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }
}