package waaph.gb.com.fragments.customerDataFormFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_responsible.*
import waaph.gb.com.BottomNavigationActivity
import waaph.gb.com.R


class ResponsibleFragment : Fragment(),View.OnClickListener {

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

    override fun onClick(v: View?) {
        when (v?.id) {
             R.id.submit -> {
                 responsible()
             }
        }
    }

    private fun responsible(){
        etValidate(edtSaledTacker)

        if (edtSaledTacker.text.toString().isNotEmpty()){
            Toast.makeText(requireContext(), "task done", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), BottomNavigationActivity::class.java)
            startActivity(intent)
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

    private fun setOnClickListener(){
        submit.setOnClickListener(this)
    }
}