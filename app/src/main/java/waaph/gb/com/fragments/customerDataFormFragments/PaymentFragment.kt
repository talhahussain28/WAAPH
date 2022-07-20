package waaph.gb.com.fragments.customerDataFormFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*
import waaph.gb.com.R
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.entities.cdf.PaymentEnt
import waaph.gb.com.utils.*

class PaymentFragment : BaseFragment(),View.OnClickListener {

    private var paymentData : PaymentEnt? = null

    private var prefs: SaveInSharedPreference? = null
    private var gson = Gson()

    private var isCash = false
    private var isCheque = false
    private var isBankTransfer = false
    private var isPayOrder = false
    private var isCreditOrDebitCard = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = SaveInSharedPreference(requireContext())

//        list = ArrayList()
//        recyclerView = paymentRecyclerView
//        adapter = PaymentAdapter(requireContext(),list)

        setOnClickListener()
        initialize()
    }

    override fun linkXML(view: View?) {

    }

    override fun setOnClickListener() {
        nextPayment.setOnClickListener(this)
    }

    override fun initialize() {
        setTextWatchers()
    }

    private fun setTextWatchers() {
        edtPersonName.addTextChangedListener(textWatcher)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.nextPayment -> {
                savePaymentInPrefs()
                //(activity as CustomerDataFormActivity).setCurrentItem(5)
                // (activity as CustomerDataFormActivity).ViewPagerAdapter(parentFragmentManager).setCurrentItem(5)
                /*val intent = Intent(requireActivity(), ComplianceAndVerificationFragment::class.java)
                startActivity(intent)*/
            }
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            ValidateEdittext()
        }
    }

    private fun ValidateEdittext(){
        Utils.etValidate(edtPersonName)

        if (edtPersonName.text.toString().isNotEmpty()){
            nextPayment.show()
        }else{
            nextPayment.hide()
        }
    }

    private fun savePaymentInPrefs() {

        getSwitchStatus()

        paymentData = PaymentEnt(
            isCash,
            isCheque,
            isBankTransfer,
            isPayOrder,
            isCreditOrDebitCard,
            edtPersonName.getTextToString()
        )

        prefs?.setString(
            Constants.ARG_PAYMENT,
            gson.toJson(paymentData)
        )
        (activity as CustomerDataFormActivity).setCurrentItem(5)
    }

    private fun getSwitchStatus() {
        isCash = customSwchSTRN.isChecked
        isCheque = isChequeSwitch.isChecked
        isBankTransfer = isBankSwitch.isChecked
        isPayOrder = isPayOrderSwitch.isChecked
        isCreditOrDebitCard = isCreditCardSwitch.isChecked
    }

}