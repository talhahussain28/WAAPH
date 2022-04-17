package waaph.gb.com.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.fragment_payment.*
import waaph.gb.com.AddPaymentActivity
import waaph.gb.com.CustomerDataFormActivity
import waaph.gb.com.R
import waaph.gb.com.adapters.ContactAdapter
import waaph.gb.com.adapters.PaymentAdapter
import waaph.gb.com.model.CreateContactModel
import waaph.gb.com.model.CreatePaymentModel


class PaymentFragment : Fragment(),View.OnClickListener {

    private lateinit var list : ArrayList<CreatePaymentModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : PaymentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = ArrayList()
        recyclerView = paymentRecyclerView
        adapter = PaymentAdapter(requireContext(),list)

        setUpRecyclerView(recyclerView,adapter)
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

    private fun setUpRecyclerView(recyclerView: RecyclerView, adapter: PaymentAdapter){
        list.add(CreatePaymentModel("waleed"))
        list.add(CreatePaymentModel("talha"))
        list.add(CreatePaymentModel("huzaifa"))
        list.add(CreatePaymentModel("anus"))
        list.add(CreatePaymentModel("ali"))
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    private fun setOnclickListeners(){
       // routeToAddPayment.setOnClickListener(this)
    }

}