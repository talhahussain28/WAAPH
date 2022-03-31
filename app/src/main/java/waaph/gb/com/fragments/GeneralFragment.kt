package waaph.gb.com.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_general.*
import waaph.gb.com.CreateGeneralDataActivity
import waaph.gb.com.CustomerDataFormActivity
import waaph.gb.com.R

class GeneralFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return   inflater.inflate(R.layout.fragment_general, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListners()

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.routeToCreateGeneral -> {
                val intent = Intent(requireActivity(), CreateGeneralDataActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun clickListners(){
        routeToCreateGeneral.setOnClickListener(this)

    }

}