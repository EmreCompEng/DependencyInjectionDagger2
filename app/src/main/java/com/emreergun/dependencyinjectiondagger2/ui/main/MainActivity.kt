package com.emreergun.dependencyinjectiondagger2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.emreergun.dependencyinjectiondagger2.BaseActivity
import com.emreergun.dependencyinjectiondagger2.R

class MainActivity : BaseActivity() {
    // define views
    private lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setViews() // set views
        initData() // init data for textview



    }

    private fun initData() {
        val sb=StringBuilder()
        val user=sessionManager.cachedUserLiveData.value?.data
        if (user!=null){
            sb.appendLine("id       :${user.id}")
            sb.appendLine("name     :${user.name}")
            sb.appendLine("username :${user.username}")
            sb.appendLine("phone    :${user.phone}")
            sb.appendLine("email    :${user.email}")
            sb.appendLine("address  :${user.address.city}")
            sb.appendLine("company  :${user.company}")
            sb.appendLine("website  :${user.website}")

            textView.text=sb.toString()
        }


    }

    private fun setViews() {
        textView=findViewById(R.id.textView)
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