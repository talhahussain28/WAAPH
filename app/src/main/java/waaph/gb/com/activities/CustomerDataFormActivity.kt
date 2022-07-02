package waaph.gb.com.activities

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_customer_data_form.*
import waaph.gb.com.R
import waaph.gb.com.fragments.customerDataFormFragments.*
import waaph.gb.com.model.ViewPagerItemModel
import waaph.gb.com.utils.BaseActivity
import waaph.gb.com.utils.SaveInSharedPreference

class CustomerDataFormActivity : BaseActivity(),View.OnClickListener {

    private val fragments: ArrayList<ViewPagerItemModel> = ArrayList()
    var viewPagerAdapter: ViewPagerAdapter?=null
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navigationView: NavigationView

    private var prefs: SaveInSharedPreference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_data_form)

        prefs = SaveInSharedPreference(this)
        prefs!!.clearSavedInSharedPreference()

        drawerLayout = drawer_layout_CDF
        navigationView = nav_view_CDF

        disableSwipeOnViewPager()

        initialize()
        setOnClickListeners()
        drawerItemListener(navigationView,drawerLayout)
    }

    private fun disableSwipeOnViewPager() {
        viewPager.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                viewPager.currentItem = viewPager.currentItem
                return true
            }
        })
    }

    override fun linkXML() { }

    override fun setOnClickListener() { }

    override fun initialize() {
        setupViewPager()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.add_dsl_iv -> {
                drawerLayout.open()
            }
            R.id.logout_CDF -> {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
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