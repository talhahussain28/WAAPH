package waaph.gb.com.fragments.CustomerDataFormFragments

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
import waaph.gb.com.CustomerDataFormActivity
import waaph.gb.com.MainActivity
import waaph.gb.com.OrderDataFormActivity
import waaph.gb.com.R

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

        drawerLayout  = drawer_layout
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
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
            R.id.add_dsl_iv -> {
                drawerLayout.open()
            }
            R.id.ODF -> {
                val intent = Intent(requireActivity(), OrderDataFormActivity::class.java)
                startActivity(intent)
            }

        }

    }

    private fun drawerItemListener() {
        val navView: NavigationView = nav_view
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_CDF ->{
                    startActivity(Intent(requireActivity(), CustomerDataFormActivity::class.java))
                    drawerLayout.close()
                }
                R.id.nav_home ->{
                    Toast.makeText(requireContext(), "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_salarySlip ->{
                    Toast.makeText(requireContext(), "Salary Slip", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_leaveRequest ->{
                    Toast.makeText(requireContext(), "leaveRequest", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_expenseClaim ->{
                    Toast.makeText(requireContext(), "expenseClaim  ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_orderDataFrom ->{
                    startActivity(Intent(requireActivity(), OrderDataFormActivity::class.java))
                    drawerLayout.close()
                }
                R.id.nav_orderPayment ->{
                    Toast.makeText(requireContext(), "orderPayment", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productLiterature ->{
                    Toast.makeText(requireContext(), "productLiterature", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_memo ->{
                    Toast.makeText(requireContext(), "memo", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_medical ->{
                    Toast.makeText(requireContext(), "medical", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_travelRequest ->{
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
    }

}