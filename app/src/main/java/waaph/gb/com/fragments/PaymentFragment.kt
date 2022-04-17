package waaph.gb.com.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_payment.*
import waaph.gb.com.AddPaymentActivity
import waaph.gb.com.CustomerDataFormActivity
import waaph.gb.com.R


class PaymentFragment : Fragment(),View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclickListeners()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {

                //(activity as CustomerDataFormActivity).setCurrentItem(5)

                // (activity as CustomerDataFormActivity).ViewPagerAdapter(parentFragmentManager).setCurrentItem(5)

                /*val intent = Intent(requireActivity(), ComplianceAndVerificationFragment::class.java)
                startActivity(intent)*/
            }

            R.id.routeToAddPayment ->{
                val intent = Intent(requireContext(),AddPaymentActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun setOnclickListeners(){
       // routeToAddPayment.setOnClickListener(this)
    }
}