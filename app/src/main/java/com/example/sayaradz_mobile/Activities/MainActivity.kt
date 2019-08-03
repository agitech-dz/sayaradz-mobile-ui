package com.example.sayaradz_mobile.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.sayaradz_mobile.Fragments.AdsFragment
import com.example.sayaradz_mobile.Fragments.GarageFragment
import com.example.sayaradz_mobile.Fragments.HomeFragment
import com.example.sayaradz_mobile.Fragments.InboxFragment
import com.example.sayaradz_mobile.Fragments.ProfileFragment
import com.example.sayaradz_mobile.R

class MainActivity : AppCompatActivity() {

    private val fm = supportFragmentManager
    private val homeFragment = HomeFragment.instance
    private val garageFragment = GarageFragment.instance
    private val adsFragment = AdsFragment.instance
    private val inboxFragment = InboxFragment.instance
    private val profileFragment = ProfileFragment.instance
    private var active:Fragment = homeFragment
    @SuppressLint("RestrictedApi")
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (active != homeFragment){
                    fm.beginTransaction().hide(active).setCustomAnimations(
                        R.anim.slide_left,
                        R.anim.slide_right
                    ).show(homeFragment).commit()
                    active = homeFragment
                }


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_garage -> {

                if (active != garageFragment){
                    fm.beginTransaction().hide(active).setCustomAnimations(
                        R.anim.slide_left,
                        R.anim.slide_right
                    ).show(garageFragment).commit()
                    active = garageFragment
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ads -> {

                if(active != adsFragment){
                    fm.beginTransaction().hide(active).setCustomAnimations(
                        R.anim.slide_left,
                        R.anim.slide_right
                    ).show(adsFragment).commit()
                    active = adsFragment
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                if( active != inboxFragment){
                    fm.beginTransaction().hide(active).setCustomAnimations(
                        R.anim.slide_left,
                        R.anim.slide_right
                    ).show(inboxFragment).commit()
                    active = inboxFragment
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                if (active != profileFragment){
                    fm.beginTransaction().hide(active).setCustomAnimations(
                        R.anim.slide_left,
                        R.anim.slide_right
                    ).show(profileFragment).commit()
                    active = profileFragment
                }

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

        supportActionBar?.hide()

        fm.beginTransaction().add(R.id.fragment_layout, garageFragment, "Garage").hide(garageFragment).commit()
        fm.beginTransaction().add(R.id.fragment_layout, adsFragment, "Ads").hide(adsFragment).commit()
        fm.beginTransaction().add(R.id.fragment_layout, inboxFragment, "Inbox").hide(inboxFragment).commit()
        fm.beginTransaction().add(R.id.fragment_layout, profileFragment, "Profile").hide(profileFragment).commit()
        fm.beginTransaction().add(R.id.fragment_layout,homeFragment, "Home").commit()
    }
}
