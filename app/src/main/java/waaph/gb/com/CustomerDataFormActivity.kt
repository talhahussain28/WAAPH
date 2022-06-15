package waaph.gb.com

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_customer_data_form.*
import kotlinx.android.synthetic.main.activity_customer_data_form.add_dsl_iv
import kotlinx.android.synthetic.main.activity_customer_data_form.tabLayout
import kotlinx.android.synthetic.main.activity_customer_data_form.viewPager
import kotlinx.android.synthetic.main.activity_order_data_form.*
import kotlinx.android.synthetic.main.fragment_home.*
import waaph.gb.com.fragments.customerDataFormFragments.*
import waaph.gb.com.model.ViewPagerItemModel
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.Dialog

class CustomerDataFormActivity : BaseActivity(),View.OnClickListener {

    private val fragments: ArrayList<ViewPagerItemModel> = ArrayList()
    var viewPagerAdapter:ViewPagerAdapter?=null
    private lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_data_form)

        drawerLayout = drawer_layout_CDF

        initialize()
        setOnClickListeners()
        drawerItemListener()
    }

    override fun linkXML() { }

    override fun setOnClickListener() { }

    override fun initialize() {
        setupViewPager()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.add_dsl_iv -> {
                openDrawerLayout()
            }
            R.id.logout_CDF -> {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        }
    }

    private fun drawerItemListener() {
        val navView: NavigationView = nav_view_CDF
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_CDF ->{
                    startActivity(Intent(this, CustomerDataFormActivity::class.java))
                    closeDrawerLayout()
                }
                R.id.nav_home ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_salarySlip ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(this, "Salary Slip", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_leaveRequest ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    drawerLayout.close()
                    Toast.makeText(this, "leaveRequest", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_expenseClaim ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(this, "expenseClaim  ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_orderDataFrom ->{
                    startActivity(Intent(this, OrderDataFormActivity::class.java))
                    closeDrawerLayout()
                }
                R.id.nav_orderPayment ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(this, "orderPayment", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productLiterature ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(this, "productLiterature", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_memo ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(this, "memo", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_medical ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(this, "medical", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_travelRequest ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout()
                    Toast.makeText(this, "travelRequest", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {  }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                if (position > 1) {
                    // java.lang.IllegalArgumentException: No view found for id 0x7f070055 (com.example.viewpagerfragmentswap:id/root_frame) for fragment SecondFragment
                    for (i in 0 until supportFragmentManager.backStackEntryCount) {
                        supportFragmentManager.popBackStack()
                    }
                }
            }
        })

    }

    fun setCurrentItem(item: Int) {
        viewPager.currentItem = item
        viewPagerAdapter?.notifyDataSetChanged()
    }

    private fun openDrawerLayout(){
        drawerLayout  = drawer_layout_CDF
        drawerLayout.open()
    }

    private fun closeDrawerLayout(){
        drawerLayout.close()
    }

    private fun setOnClickListeners(){
        add_dsl_iv.setOnClickListener(this)
        logout_CDF.setOnClickListener(this)
    }

    inner class ViewPagerAdapter internal constructor(fm: FragmentManager)
        : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        init {
            fragments.add(ViewPagerItemModel("General",
                GeneralFragment()
            ))
            fragments.add(ViewPagerItemModel("Address",
                AddressFragment()
            ))
            fragments.add(ViewPagerItemModel("Contact",
                ContactFragment()
            ))
            fragments.add(ViewPagerItemModel("Bank",
                BankFragment()
            ))
            fragments.add(ViewPagerItemModel("Payment",
                PaymentFragment()
            ))
            fragments.add(ViewPagerItemModel("Compliance And Verification",
                ComplianceAndVerificationFragment()
            ))
            fragments.add(ViewPagerItemModel("Responsible",
                ResponsibleFragment()
            ))
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position].fragment
        }

        override fun getPageTitle(position: Int): CharSequence {
            return fragments[position].title
        }
    }

}