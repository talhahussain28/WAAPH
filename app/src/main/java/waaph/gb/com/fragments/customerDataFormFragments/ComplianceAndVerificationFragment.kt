package waaph.gb.com.fragments.customerDataFormFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_compliance_and_verification.*
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.R
import waaph.gb.com.utils.BaseFragment

class ComplianceAndVerificationFragment : BaseFragment(),View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compliance_and_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        initialize()
    }

    override fun linkXML(view: View?) {

    }

    override fun setOnClickListener() {
        next.setOnClickListener(this)
    }

    override fun initialize() {
    }

    companion object fun newInstance(){

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {
                (activity as CustomerDataFormActivity).setCurrentItem(6)

            }

        }
    }
}