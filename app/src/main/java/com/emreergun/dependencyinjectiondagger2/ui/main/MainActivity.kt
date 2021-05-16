package com.emreergun.dependencyinjectiondagger2.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
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
                val navOptions =NavOptions.Builder()
                    .setPopUpTo(R.id.main_nav,true)
                    .build()
                navController.navigate(R.id.profileFragment,null,navOptions) // Novigat Other Fragment
            }
            R.id.posts_drawer->{
                // Tekrardan aynı fragmente stack yerleştirmemesi için zaten aynı fragmentta ise bir değişiklik yapma
                if (isValidDestination(R.id.postFragment)){
                    navController.navigate(R.id.postFragment) // Novigat Other Fragment
                }

            }
            android.R.id.home->{
                Toast.makeText(this,"Home Basıldı",Toast.LENGTH_SHORT).show()
                return if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                } else
                    false

            }
        }
        item.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun isValidDestination(destination:Int): Boolean {
        return destination!=navController.currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,drawerLayout) || super.onSupportNavigateUp()
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