package waaph.gb.com.fragments.customerDataFormFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_responsible.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import waaph.gb.com.R
import waaph.gb.com.database.cdf.GeneralDatabase
import waaph.gb.com.entities.cdf.*
import waaph.gb.com.entities.cdf.GeneralEnt
import waaph.gb.com.utils.*


class ResponsibleFragment : BaseFragment(),View.OnClickListener {

    private lateinit var generalDatabase: GeneralDatabase

    private var generalData : GeneralEnt? = null
    private var addressList =  ArrayList<AddressEnt>()
    private var contactList =  ArrayList<ContactEnt>()
    private var bankList =  ArrayList<BankEnt>()
    private var paymentData : PaymentEnt? = null

    private var prefs: SaveInSharedPreference? = null
    private var gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_responsible, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generalDatabase = GeneralDatabase.getInstance(context!!)
        prefs = SaveInSharedPreference(requireContext())

        edtSaledTacker.requestFocus()
        showKeyBoard()

        initialize()
        setOnClickListener()
    }

    override fun linkXML(view: View?) {

    }

    override fun setOnClickListener() {
        submit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
             R.id.submit -> {
                 fetchDataFromPrefs()
             }
        }
    }

    override fun initialize() {
        setTextWatchers()
    }

    private fun fetchDataFromPrefs() {

        generalData = Gson().fromJson(
        SaveInSharedPreference(requireContext()).getString(Constants.ARG_GENERAL),
        GeneralEnt::class.java
        )

        addressList = getAddressList()
        /*contactList = getContactList()
        bankList = getBankList()

        paymentData = Gson().fromJson(
        SaveInSharedPreference(requireContext()).getString(Constants.ARG_PAYMENT),
        PaymentEnt::class.java
        )*/
        CoroutineScope(Dispatchers.IO).launch {
            putDataInRoom()
        }
    }

    private suspend fun putDataInRoom() {
        generalDatabase.generalDao.addGeneral(generalData!!)
    }

    private fun getAddressList(): ArrayList<AddressEnt> {
        val type = object : TypeToken<ArrayList<AddressEnt>>() {}.type!!
        return gson.fromJson(prefs!!.getString(Constants.ARG_ADDRESS), type)
    }

    private fun getContactList(): ArrayList<ContactEnt> {
        val type = object : TypeToken<ArrayList<ContactEnt>>() {}.type!!
        return gson.fromJson(prefs!!.getString(Constants.ARG_CONTACT), type)
    }

    private fun getBankList(): ArrayList<BankEnt> {
        val type = object : TypeToken<ArrayList<BankEnt>>() {}.type!!
        return gson.fromJson(prefs!!.getString(Constants.ARG_BANK), type)
    }


    private fun setTextWatchers() {
        edtSaledTacker.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            ValidateEdittext()
        }
    }

    private fun ValidateEdittext(){
        if (Utils.etValidate(edtSaledTacker)){
            submit.show()
        }else{
            submit.gone()
        }
    }

    private fun etValidate(edittext: TextInputEditText): Boolean {
        var validate = edittext.text.toString()
        validate = validate.replace("\\s+".toRegex(), " ").trim { it <= ' ' }
        if (validate.isEmpty()) {
            edittext.error = "Required"
            return false
        }
        return true
    }


}