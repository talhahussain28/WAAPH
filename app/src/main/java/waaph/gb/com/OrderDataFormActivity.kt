package waaph.gb.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import waaph.gb.com.fragments.customerDataFormFragments.orderDataFormFragment.ComplianceFragment
import waaph.gb.com.fragments.customerDataFormFragments.orderDataFormFragment.GeneralFragmentODF
import waaph.gb.com.fragments.customerDataFormFragments.orderDataFormFragment.ItemSelectionFragment
import waaph.gb.com.model.ViewPagerItemModel
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.Dialog

class OrderDataFormActivity : BaseActivity(),View.OnClickListener {

    private val fragments: ArrayList<ViewPagerItemModel> = ArrayList()
     var viewPagerAdapter: ViewPagerAdapter?=null
    private lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_data_form)

        drawerLayout = drawer_layout_ODF

        initialize()
        setOnClickListeners()
        drawerItemListener()
    }

    override fun linkXML() {

    }

    override fun setOnClickListener() {
    }

    override fun initialize() {
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position > 1) {
                    for (i in 0 until supportFragmentManager.backStackEntryCount) {
                        supportFragmentManager.popBackStack()
                    }
                }
            }
        })

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.add_dsl_iv -> {
                openDrawerLayout()
            }
            R.id.logout_ODF -> {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }

        }
    }

    private fun drawerItemListener() {
        val navView: NavigationView = nav_view_ODF
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

    fun setCurrentItem(item: Int) {
        viewPager.currentItem = item
        viewPagerAdapter?.notifyDataSetChanged()
    }

    private fun openDrawerLayout(){
        drawerLayout  = drawer_layout_ODF
        drawerLayout.open()
    }

    private fun closeDrawerLayout(){
        drawerLayout.close()
    }

    private fun setOnClickListeners(){
        add_dsl_iv.setOnClickListener(this)
        logout_ODF.setOnClickListener(this)
    }


    inner class ViewPagerAdapter internal constructor(fm: FragmentManager)
        : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        init {
            fragments.add(ViewPagerItemModel("General",
                GeneralFragmentODF()
            ))
            fragments.add(ViewPagerItemModel("ItemSelection",
                ItemSelectionFragment()
            ))
            fragments.add(ViewPagerItemModel("Compliance",
                ComplianceFragment()
            ))
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position].fragment
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragments[position].title
        }
    }

}

