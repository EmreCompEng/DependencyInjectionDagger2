package com.emreergun.dependencyinjectiondagger2.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.emreergun.dependencyinjectiondagger2.BaseActivity
import com.emreergun.dependencyinjectiondagger2.R
import com.emreergun.dependencyinjectiondagger2.ui.main.profile.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : NavigationView.OnNavigationItemSelectedListener, BaseActivity()  {

    private lateinit var navView:NavigationView
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navController:NavController

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile_drawer->{
                navController.navigate(R.id.profileFragment) // Novigat Other Fragment
            }
            R.id.posts_drawer->{
                navController.navigate(R.id.postFragment) // Novigat Other Fragment
            }
        }
        item.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView=findViewById(R.id.nav_view)
        drawerLayout=findViewById(R.id.mainDrawerLayout)
        initViews()




    }

    private fun initViews() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(navView,navController)
        navView.setNavigationItemSelectedListener(this)
    }


    // menu Inflater
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout_menu_item->{
                Log.d(TAG, "onOptionsItemSelected: logout")
                sessionManager.logOut()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG = "MainActivity"
    }


}