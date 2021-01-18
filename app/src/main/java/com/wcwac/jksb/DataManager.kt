package com.wcwac.jksb

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson


object DataManager {
    private var targetHour = 1
    private var targetMinute = 0
    private lateinit var log: String
    private const val fileKey = "userdata"
    private const val key = "data"
    private lateinit var app: MyApplication
    private lateinit var data: UsersType
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    fun init(a: MyApplication) {
        app = a
        sharedPreferences = app.applicationContext.getSharedPreferences(fileKey, MODE_PRIVATE)
        loadData()
    }

    private fun saveData() {
        try {
            val json = Gson().toJson(data)
            editor = sharedPreferences.edit()
            editor.putString(key, json)
            editor.putInt("targetHour", targetHour)
            editor.putInt("targetMinute", targetMinute)
            editor.putString("log", log)
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadData() {
        try {
            targetHour = sharedPreferences.getInt("targetHour", 1)
            targetMinute = sharedPreferences.getInt("targetMinute", 0)
            log = sharedPreferences.getString("log", "").toString()
            val json = sharedPreferences.getString(key, "")
            if (json == null || json.isEmpty()) {
                data = UsersType(ArrayList())
                return
            }
            data = Gson().fromJson(json, UsersType::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun insertData(user: UsersType.User, index: Int): Int {
        try {
            if (index >= 0) {
                //edit
                data.users[index] = user
            } else {
                //add
                data.users.add(user)
            }
            addLog("${Utils().getTime()} edit user ${data.users[index].username}")
            saveData()
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
        return 0
    }

    fun removeData(index: Int): Int {
        try {
            if (index < 0 || index > data.users.count() - 1) {
                return -1
            }
            addLog("${Utils().getTime()} remove user ${data.users[index].username}")
            data.users.removeAt(index)
            saveData()
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
        return 0
    }

    fun getData(): UsersType {
        return data
    }

    fun setData(users: UsersType) {
        data = users
    }


    fun setTargetTime(hh: Int, mm: Int) {
        targetHour = hh
        targetMinute = mm
        addLog("time change to $targetHour:$targetMinute")
    }

    fun getTargetTime(): Pair<Int, Int> {
        return Pair(targetHour, targetMinute)
    }

    fun addLog(str: String) {
        log += str + "\n"
        saveData()
    }

    fun delLog() {
        log = ""
        saveData()
    }

    fun getLog(): String {
        return log
    }
}