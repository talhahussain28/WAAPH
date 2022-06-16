package waaph.gb.com.navDrawer

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import waaph.gb.com.R
import waaph.gb.com.activities.BottomNavigationActivity
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.activities.OrderDataFormActivity
import waaph.gb.com.utils.Dialog

open class NavDrawer() : AppCompatActivity() {

     fun drawerItemListener(navigationView: NavigationView,drawerLayout : DrawerLayout) {
        val navView = navigationView
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_CDF ->{
                    if (this is CustomerDataFormActivity){
                        closeDrawerLayout(drawerLayout)
                    } else{
                        startActivity(Intent(this, CustomerDataFormActivity::class.java))
                        closeDrawerLayout(drawerLayout)
                    }
                }
                R.id.nav_home ->{
                    startActivity(Intent(this, BottomNavigationActivity::class.java))
                    finish()
                  /*  val fragment: Fragment = HomeFragment()
                    val fragmentManager: FragmentManager = this.supportFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.drawer_layout_Home, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()*/
                  /*  val intent = Intent(this,HomeFragment::class.java)
                    startActivity(intent)*/
        //            this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout(drawerLayout)
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_salarySlip ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout(drawerLayout)
                    Toast.makeText(this, "Salary Slip", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_leaveRequest ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout(drawerLayout)
                    Toast.makeText(this, "leaveRequest", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_expenseClaim ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout(drawerLayout)
                    Toast.makeText(this, "expenseClaim  ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_orderDataFrom ->{
                    if (this is OrderDataFormActivity){
                        closeDrawerLayout(drawerLayout)
                    } else{
                        startActivity(Intent(this, OrderDataFormActivity::class.java))
                        closeDrawerLayout(drawerLayout)
                    }
                }
                R.id.nav_orderPayment ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout(drawerLayout)
                    Toast.makeText(this, "orderPayment", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productLiterature ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout(drawerLayout)
                    Toast.makeText(this, "productLiterature", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_memo ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout(drawerLayout)
                    Toast.makeText(this, "memo", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_medical ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout(drawerLayout)
                    Toast.makeText(this, "medical", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_travelRequest ->{
                    this.supportFragmentManager.let { Dialog().show(it, "MyCustomFragment") }
                    closeDrawerLayout(drawerLayout)
                    Toast.makeText(this, "travelRequest", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun closeDrawerLayout(drawerLayout : DrawerLayout){
        drawerLayout.close()
    }
}