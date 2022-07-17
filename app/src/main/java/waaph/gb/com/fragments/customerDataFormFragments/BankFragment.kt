package waaph.gb.com.fragments.customerDataFormFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_bank.*
import waaph.gb.com.R
import waaph.gb.com.activities.BankDetailActivity
import waaph.gb.com.activities.CreateBankActivity
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.adapters.BankAdapter
import waaph.gb.com.entities.cdf.BankEnt
import waaph.gb.com.interfaces.OnRecyclerViewItemClickListener
import waaph.gb.com.utils.*

class BankFragment : BaseFragment(), View.OnClickListener, OnRecyclerViewItemClickListener<BankEnt> {

    companion object{
        const val CREATE_BANK_CODE = 1003
    }

    private var list =  ArrayList<BankEnt>()
    private lateinit var adapter: BankAdapter
    private var bankData: BankEnt? = null

    private var prefs: SaveInSharedPreference? = null
    private var gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = SaveInSharedPreference(requireContext())

        fab.setOnClickListener(this)
        btnNext.setOnClickListener(this)

        setUpRecyclerViewData()

        if (!prefs!!.getString(Constants.ARG_BANK).isNullOrEmpty()){
            btnNext.show()
        }else{
            btnNext.gone()
        }
    }

    override fun linkXML(view: View?) {
    }

    override fun setOnClickListener() {
        btnNext.setOnClickListener(this)
        fab.setOnClickListener(this)

    }

    override fun initialize() {
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
            R.id.fab -> {
                val intent = Intent(requireActivity(), CreateBankActivity::class.java)
                startActivityForResult(intent, CREATE_BANK_CODE)
            }

            R.id.btnNext -> {
                (activity as CustomerDataFormActivity).setCurrentItem(4)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                CREATE_BANK_CODE -> {
                    bankData = gson.fromJson(data!!.extras!!.getString("data"),
                        BankEnt::class.java)

                    list.add(bankData!!)

                    // Update and get new list from prefs
                    prefs!!.setString(Constants.ARG_BANK, gson.toJson(list))
                    adapter.updateList(getNewList())

                    btnNext.show()
                }
            }
        }
    }

    private fun getNewList(): ArrayList<BankEnt> {
        val type = object : TypeToken<ArrayList<BankEnt>>() {}.type!!
        return gson.fromJson(prefs!!.getString(Constants.ARG_BANK), type)
    }

    private fun setUpRecyclerViewData() {
        adapter = BankAdapter(requireContext(), ArrayList(), this)
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    override fun onItemClick(itemView: View?, data: BankEnt?, position: Int) {
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