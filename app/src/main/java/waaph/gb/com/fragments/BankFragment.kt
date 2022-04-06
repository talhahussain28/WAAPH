package waaph.gb.com.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_bank.*
import waaph.gb.com.BankDetailActivity
import waaph.gb.com.CreateBankActivity
import waaph.gb.com.CustomerDataFormActivity
import waaph.gb.com.R
import waaph.gb.com.adapters.BankAdapter
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.model.Data
import waaph.gb.com.utils.BaseFragment

class BankFragment : BaseFragment(),View.OnClickListener, OnRecyclerViewItemClickListener<Data> {
    private var adapter: BankAdapter? = null
    private var list = ArrayList<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        //next.setOnClickListener(this)
       // bank_layout.setOnClickListener(this)
        setOnClickListener()
        setRecyclerview()

    }

    override fun linkXML(view: View?) {
    }

    override fun setOnClickListener() {
        fab.setOnClickListener(this)

    }

    override fun initialize() {
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
           /* R.id.bank_layout ->{
                pickerActionDialog()
            }*/
            R.id.fab ->{
                val intent = Intent(requireActivity(), CreateBankActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun setRecyclerview() {
       // list = arrayListOf()
        list.add(Data("Meezan bank", "None"))
        list.add(Data("Alfa bank", "None"))
        list.add(Data("Js bank", "None"))
        list.add(Data("HBL bank", "None"))
        list.add(Data("Habib bank", "None"))
        list.add(Data("AL Habib bank", "None"))
        adapter = BankAdapter(requireContext(), list,this )
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    override fun onItemClick(itemView: View?/*, data: Data?*/, position: Int) {
       /* data?.let {*/
            val intent = Intent(requireContext(), BankDetailActivity::class.java)
            //intent.putExtra("data", Gson().toJson(data))
            startActivity(intent)

           // overridePendingTransition(R.anim.slide_up, android.R.anim.fade_out)
       /* }*/
    }
    /* private fun pickerActionDialog() {

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

     }*/
}