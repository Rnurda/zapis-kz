package com.ryspay.zapiskz.common
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ryspay.zapiskz.home_screen.HomeActivity
import com.ryspay.zapiskz.ProfileActivity
import com.ryspay.zapiskz.R
import com.ryspay.zapiskz.SearchActivity
import kotlinx.android.synthetic.main.bottom_navigation_view.*

abstract class BaseActivity(val navNumber: Int): AppCompatActivity() {

    fun setupBottomNavigation() {
        bottom_navigation_view.setIconSize(29f, 29f)
            .setTextVisibility(true)
            .enableItemShiftingMode(false)
            .enableShiftingMode(false)
            .enableAnimation(false)
        for (i in 0 until bottom_navigation_view.menu.size()) {
            bottom_navigation_view.setIconTintList(i, null)
        }
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            val nextActivity =
                when (it.itemId) {
                    R.id.nav_item_home -> HomeActivity::class.java
                    R.id.nav_item_search -> SearchActivity::class.java
                    R.id.nav_item_profile -> ProfileActivity::class.java
                    else -> {
                        Log.e("&&&&&&&&", "unknown nav item clicked $it")
                        null
                    }
                }
            if (nextActivity != null) {
                val intent = Intent(this, nextActivity)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0, 0)
                true
            } else {
                false
            }
        }
        bottom_navigation_view.menu.getItem(navNumber).isChecked = true
    }
    companion object{
        const val ID_MESSAGE = "com.ryspay.zapiskz.ID"
        const val RATING_MESSAGE = "RATING_MESSAGE"
        const val BASE_URL = "http://zp.jgroup.kz/rest/clients-app/"
    }
}