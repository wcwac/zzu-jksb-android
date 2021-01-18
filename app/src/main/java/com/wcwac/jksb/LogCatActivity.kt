package com.wcwac.jksb

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LogCatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logcat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<TextView>(R.id.log_textView).text = DataManager.getLog()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        DataManager.delLog()
        return super.onOptionsItemSelected(item)
    }
}