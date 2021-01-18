package com.wcwac.jksb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService.enqueueWork


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

//        LoginWorker.enqueueWork()
//        val intent=Intent()
//        LoginWorker.enqueueWork(context, intent)
        enqueueWork(context, LoginWorker::class.java, 12345, intent)
//        val a = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"))
//        val b = a.get(Calendar.HOUR_OF_DAY) * 100 + a.get(Calendar.MINUTE)
//        DataManager.insertData(UsersType.User(b.toString()), -1)
//        DataManager.getTargetTime()
        MyApplication.instance.startAlarm()
    }

}
