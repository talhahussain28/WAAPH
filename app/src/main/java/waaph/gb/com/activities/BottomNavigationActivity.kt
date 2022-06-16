package waaph.gb.com.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import waaph.gb.com.R
import waaph.gb.com.fragments.customerDataFormFragments.HomeFragment
import waaph.gb.com.utils.BaseActivity

class BottomNavigationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        initialize()
        setOnClickListener()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    title = "Home"
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_plans -> {
                    //title = resources.getString(R.string.home)
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                /* R.id.navigation_settings -> {
                     title = resources.getString(R.string.settings)
                     loadFragment(SettingsFragment())
                     return@setOnNavigationItemSelectedListener true
                 }*/

            }
            false
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun linkXML() {

    }

    override fun setOnClickListener() {
    }

    override fun initialize() {
        loadFragment(HomeFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
