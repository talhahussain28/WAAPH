package waaph.gb.com.fragments.customerDataFormFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_responsible.*
import waaph.gb.com.R
import waaph.gb.com.utils.BaseFragment
import waaph.gb.com.utils.Utils
import waaph.gb.com.utils.gone
import waaph.gb.com.utils.show


class ResponsibleFragment : BaseFragment(),View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_responsible, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
    }

    override fun linkXML(view: View?) {

    }

    override fun setOnClickListener() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
             R.id.submit -> {

             }
        }
    }

    override fun initialize() {
        setTextWatchers()
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
        Utils.etValidate(edtSaledTacker)

        if (edtSaledTacker.text.toString().isNotEmpty()){
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