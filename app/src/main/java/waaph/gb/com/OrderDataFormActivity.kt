package waaph.gb.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_customer_data_form.*
import waaph.gb.com.fragments.CustomerDataFormFragments.*
import waaph.gb.com.fragments.CustomerDataFormFragments.OrderDataFormFragment.ComplianceFragment
import waaph.gb.com.fragments.CustomerDataFormFragments.OrderDataFormFragment.GeneralFragmentODF
import waaph.gb.com.fragments.CustomerDataFormFragments.OrderDataFormFragment.ItemSelectionFragment
import waaph.gb.com.model.ViewPagerItemModel

class OrderDataFormActivity : AppCompatActivity() {

    private val fragments: ArrayList<ViewPagerItemModel> = ArrayList()
     var viewPagerAdapter: ViewPagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_data_form)
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
                    // java.lang.IllegalArgumentException: No view found for id 0x7f070055 (com.example.viewpagerfragmentswap:id/root_frame) for fragment SecondFragment
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

