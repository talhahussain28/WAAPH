package waaph.gb.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_customer_data_form.*
import waaph.gb.com.fragments.*
import waaph.gb.com.model.ViewPagerItemModel
import java.util.ArrayList

class CustomerDataFormActivity : AppCompatActivity() {
    private val fragments: ArrayList<ViewPagerItemModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_data_form)
        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }
    inner class ViewPagerAdapter internal constructor(
        fm: FragmentManager
    ) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        init {
            fragments.add(ViewPagerItemModel("General",
                GeneralFragment()))
            fragments.add(ViewPagerItemModel("Address",
                AddressFragment()))
            fragments.add(ViewPagerItemModel("Contact",
                ContactFragment()))
            fragments.add(ViewPagerItemModel("Bank",
                BankFragment()))
            fragments.add(ViewPagerItemModel("Payment",
                PaymentFragment()))
            fragments.add(ViewPagerItemModel("Compliance And Verification",
                ComplianceAndVerificationFragment()))
            fragments.add(ViewPagerItemModel("Responsible",
                ResponsibleFragment()))
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