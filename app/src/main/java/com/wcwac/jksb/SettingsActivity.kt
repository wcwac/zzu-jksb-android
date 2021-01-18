package com.wcwac.jksb

import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<TextView>(R.id.setting_hour).text = DataManager.getTargetTime().first.toString()
        findViewById<TextView>(R.id.setting_minute).text = DataManager.getTargetTime().second.toString()
    }

    private fun onSubmitTimeChanged() {
        MyApplication.instance.cancelAlarm(0)
        val hourMinuteArray = DataManager.getTargetTime()
        MyApplication.instance.setAlarm(hourMinuteArray.first, hourMinuteArray.second)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        try {
            DataManager.setTargetTime(
                    findViewById<TextView>(R.id.setting_hour).text.toString().toInt(),
                    findViewById<TextView>(R.id.setting_minute).text.toString().toInt()
            )
            onSubmitTimeChanged()
            finish()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                DataManager.setTargetTime(
                        findViewById<TextView>(R.id.setting_hour).text.toString().toInt(),
                        findViewById<TextView>(R.id.setting_minute).text.toString().toInt()
                )
                onSubmitTimeChanged()
                finish()

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
