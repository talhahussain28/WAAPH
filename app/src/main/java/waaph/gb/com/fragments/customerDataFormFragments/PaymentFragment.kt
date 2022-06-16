package waaph.gb.com.fragments.customerDataFormFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_payment.*
import waaph.gb.com.R
import waaph.gb.com.utils.Utils

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

//        list = ArrayList()
//        recyclerView = paymentRecyclerView
//        adapter = PaymentAdapter(requireContext(),list)

        setOnclickListeners()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.nextPayment -> {
                addPayment()
                //(activity as CustomerDataFormActivity).setCurrentItem(5)
                // (activity as CustomerDataFormActivity).ViewPagerAdapter(parentFragmentManager).setCurrentItem(5)
                /*val intent = Intent(requireActivity(), ComplianceAndVerificationFragment::class.java)
                startActivity(intent)*/
            }
        }
    }

    private fun addPayment(){
        Utils.etValidate(edtPersonName)

        if (edtPersonName.text.toString().isNotEmpty()){
            Toast.makeText(context, "task done", Toast.LENGTH_SHORT).show()
        }
    }



    private fun setOnclickListeners(){
        nextPayment.setOnClickListener(this)
    }

}