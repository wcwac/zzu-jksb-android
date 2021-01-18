package com.wcwac.jksb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val adapter by lazy { MyRecyclerAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val drawerLayout = findViewById<DrawerLayout>(R.id.dw_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
//        val timerHandler: Handler = Handler()
//        val timerRunnable: Runnable = object : Runnable {
//            override fun run() {
//                // Here you can update your adapter data
//                adapter.notifyDataSetChanged()
//                timerHandler.postDelayed(this, 1000) //run every second
//            }
//        }
//
//        timerHandler.postDelayed(timerRunnable, 1000) //Start timer after 1 sec


//        LoginWorker.init(this)
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_play -> {
            Thread {
                LoginWorker().workAllUsers()
            }.start()
            true
        }
        R.id.action_settings -> {
            val intent = Intent()
            this.startActivity(intent.setClass(this, EditorActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    public override fun onResume() {
        super.onResume()
        adapter.update()
    }

    public override fun onPause() {
        super.onPause()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            //R.id.server_profile -> activityClass = MainActivity::class.java
            R.id.tutorial -> {
                val tutorialUrl = "https://www.github.com/wcwac/zzu-jksb-android"
                this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(tutorialUrl)))
            }
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.feedback -> {
                val issueUrl = "https://www.github.com/wcwac/zzu-jksb-android/issues"
                this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(issueUrl)))
            }
            R.id.about -> {
                val aboutUrl = "https://www.github.com/wcwac/zzu-jksb-android"
                this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(aboutUrl)))
            }
            R.id.logcat -> {
                startActivity(Intent(this, LogCatActivity::class.java))
            }
        }
//        findViewById<NavigationView>(R.menu.menu_drawer).closeDrawer(GravityCompat.START)
        return true
    }
}
