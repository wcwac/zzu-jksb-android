package com.wcwac.jksb

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.AlarmManagerCompat.setExactAndAllowWhileIdle
import java.util.*

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        DataManager.init(this)
        startAlarm()
    }

    fun startAlarm() {
        val hourMinuteArray = DataManager.getTargetTime()
        setAlarm(hourMinuteArray.first, hourMinuteArray.first)
    }

    fun cancelAlarm(requestCode: Int = 1) {
        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(
                PendingIntent.getBroadcast(this, requestCode, Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_UPDATE_CURRENT))
    }

    fun setAlarm(hour: Int, minute: Int) {
        val now = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"))
        val targetTime = now.clone() as Calendar
        targetTime.set(Calendar.HOUR_OF_DAY, hour)
        targetTime.set(Calendar.MINUTE, minute)
        targetTime.set(Calendar.SECOND, 0)
        targetTime.set(Calendar.MILLISECOND, 0)
        if (targetTime.before(now)) targetTime.add(Calendar.DATE, 1)
        setAlarm(targetTime.timeInMillis)
    }

    private fun setAlarm(triggerAtMillis: Long) {
        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        setExactAndAllowWhileIdle(alarmManager, RTC_WAKEUP, triggerAtMillis, PendingIntent.getBroadcast(this, 0, Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_CANCEL_CURRENT))
    }
}