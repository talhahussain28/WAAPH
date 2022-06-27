package waaph.gb.com.fragments.customerDataFormFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_home.*
import waaph.gb.com.*
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.activities.MainActivity
import waaph.gb.com.activities.OrderDataFormActivity
import waaph.gb.com.utils.Dialog

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var toogle: ActionBarDrawerToggle
    private lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout  = drawer_layout_Home
        toogle =
            ActionBarDrawerToggle(requireActivity(), drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        drawerItemListener()
        clickListeners()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.CDF -> {
                val intent = Intent(requireActivity(), CustomerDataFormActivity::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                startActivity(Intent(requireContext(),MainActivity::class.java))
            }
            R.id.add_dsl_iv -> {
                openDrawerLayout()
            }
            R.id.ODF -> {
                val intent = Intent(requireActivity(), OrderDataFormActivity::class.java)
                startActivity(intent)
                activity!!.finish()
            }
            R.id.cardView_my_profile ->{
                activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }

                /* val intent = Intent(requireActivity(), MyProfileActivity::class.java)
                 startActivity(intent)*/

            }
            R.id.cardView_salaryslip ->{
                activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }

            }
            R.id.cardView_leaveRequest ->{
                activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
            }
            R.id.cardView_expenseClaim ->{
                activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
            }
            R.id.cardView_ordePayment ->{
                activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
            }
            R.id.cardView_expClaim ->{
                activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
            }
            R.id.cardView_memo ->{
                activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
            }
            R.id.cardView_medical ->{
                activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
            }
            R.id.cardView_travel ->{
                activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
            }
        }

    }

    private fun drawerItemListener() {
        val navView: NavigationView = nav_view
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_CDF ->{
                    startActivity(Intent(requireActivity(), CustomerDataFormActivity::class.java))
                    closeDrawerLayout()
                }
                R.id.nav_home ->{
                    closeDrawerLayout()
                }
                R.id.nav_salarySlip ->{
                    activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(requireContext(), "Salary Slip", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_leaveRequest ->{
                    activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
                    drawerLayout.close()
                    Toast.makeText(requireContext(), "leaveRequest", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_expenseClaim ->{
                    activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(requireContext(), "expenseClaim  ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_orderDataFrom ->{
                    startActivity(Intent(requireActivity(), OrderDataFormActivity::class.java))
                    closeDrawerLayout()
                }
                R.id.nav_orderPayment ->{
                    activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(requireContext(), "orderPayment", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productLiterature ->{
                    activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(requireContext(), "productLiterature", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_memo ->{
                    activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(requireContext(), "memo", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_medical ->{
                    activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(requireContext(), "medical", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_travelRequest ->{
                    activity?.supportFragmentManager?.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(requireContext(), "travelRequest", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun clickListeners() {
        CDF.setOnClickListener(this)
        logout.setOnClickListener(this)
        add_dsl_iv.setOnClickListener(this)
        ODF.setOnClickListener(this)
        cardView_my_profile.setOnClickListener(this)
        cardView_salaryslip.setOnClickListener(this)
        cardView_leaveRequest.setOnClickListener(this)
        cardView_travel.setOnClickListener(this)
        cardView_medical.setOnClickListener(this)
        cardView_leaveRequest.setOnClickListener(this)
        cardView_expenseClaim.setOnClickListener(this)
        cardView_ordePayment.setOnClickListener(this)
        cardView_expClaim.setOnClickListener(this)
        cardView_memo.setOnClickListener(this)
    }

    private fun openDrawerLayout(){
        drawerLayout  = drawer_layout_Home
        drawerLayout.open()
    }

    private fun closeDrawerLayout(){
        drawerLayout.close()
    }

}