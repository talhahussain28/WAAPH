package waaph.gb.com.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_customer_data_form.*
import waaph.gb.com.R
import waaph.gb.com.fragments.customerDataFormFragments.orderDataFormFragment.ComplianceFragment
import waaph.gb.com.fragments.customerDataFormFragments.orderDataFormFragment.GeneralFragmentODF
import waaph.gb.com.fragments.customerDataFormFragments.orderDataFormFragment.ItemSelectionFragment
import waaph.gb.com.model.ViewPagerItemModel
import waaph.gb.com.utils.BaseActivity

class OrderDataFormActivity : BaseActivity() {

    private val fragments: ArrayList<ViewPagerItemModel> = ArrayList()
     var viewPagerAdapter: ViewPagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_data_form)
        initialize()
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

    fun setCurrentItem(item: Int) {
        viewPager.currentItem = item
        viewPagerAdapter?.notifyDataSetChanged()
    }

}

