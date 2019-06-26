package com.example.sayaradz_mobile

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val fm = supportFragmentManager
    private val homeFragment = HomeFragment.instance
    private val garageFragment = GarageFragment.instance
    private val adsFragment = AdsFragment.instance
    private val inboxFragment = InboxFragment.instance
    private val profileFragment = ProfileFragment.instance
    private var active:Fragment = homeFragment
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                fm.beginTransaction().hide(active).show(homeFragment).commit()
                active = homeFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_garage -> {

                fm.beginTransaction().hide(active).show(garageFragment).commit()
                active = garageFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ads -> {

                fm.beginTransaction().hide(active).show(adsFragment).commit()
                active = adsFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                fm.beginTransaction().hide(active).show(inboxFragment).commit()
                active = inboxFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {

                fm.beginTransaction().hide(active).show(profileFragment).commit()
                active = profileFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        fm.beginTransaction().add(R.id.fragment_layout, garageFragment, "Garage").hide(garageFragment).commit()
        fm.beginTransaction().add(R.id.fragment_layout, adsFragment, "Ads").hide(adsFragment).commit()
        fm.beginTransaction().add(R.id.fragment_layout, inboxFragment, "Inbox").hide(inboxFragment).commit()
        fm.beginTransaction().add(R.id.fragment_layout, profileFragment, "Profile").hide(profileFragment).commit()
        fm.beginTransaction().add(R.id.fragment_layout,homeFragment, "Home").commit()
    }
}
