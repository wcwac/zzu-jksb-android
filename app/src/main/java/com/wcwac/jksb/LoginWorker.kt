package com.wcwac.jksb

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class LoginWorker : JobIntentService() {
    //    private lateinit var myActivity: MainActivity
    private val JOB_ID: Int = 12345

    fun enqueueWork(context: Context, work: Intent) {
        enqueueWork(context, LoginWorker::class.java, JOB_ID, work)
    }

    private fun sendPostRequest(url: String, reqParam: String): String {

        val mURL = URL(url)
        var response: StringBuffer
        with(mURL.openConnection() as HttpURLConnection) {
            requestMethod = "POST"
            val wr = OutputStreamWriter(outputStream)
            wr.write(reqParam)
            wr.flush()
            BufferedReader(InputStreamReader(inputStream)).use {
                response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
            }
        }
        return response.toString()
    }

    private fun doInBackground(user: UsersType.User): Boolean {
        try {
            val ret =
                URL("https://jksb.v.zzu.edu.cn/vls6sss/zzujksb.dll/login?uid=${user.username}&upw=${user.password}").readText()
            val str = "(?<=location=\").*?(?=\")".toRegex().find(ret)?.value.toString()
            val ptopid = "(?<=ptopid=).*?(?=&)".toRegex().find(str)?.value.toString()
            val sid = "(?<=sid=)[0-9A-Za-z]*".toRegex().find(str)?.value.toString()
            sendPostRequest(
                "https://jksb.v.zzu.edu.cn/vls6sss/zzujksb.dll/jksb",
                "day6=b&did=1&men6=a&ptopid=${ptopid}&sid=${sid}"
            )

            val ans = sendPostRequest(
                "https://jksb.v.zzu.edu.cn/vls6sss/zzujksb.dll/jksb",
                "myvs_1=${user.myvs_1}&" +
                        "myvs_2=${user.myvs_2}&" +
                        "myvs_3=${user.myvs_3}&" +
                        "myvs_4=${user.myvs_4}&" +
                        "myvs_5=${user.myvs_5}&" +
                        "myvs_6=${user.myvs_6}&" +
                        "myvs_7=${user.myvs_7}&" +
                        "myvs_8=${user.myvs_8}&" +
                        "myvs_9=${user.myvs_9}&" +
                        "myvs_10=${user.myvs_10}&" +
                        "myvs_11=${user.myvs_11}&" +
                        "myvs_12=${user.myvs_12}&" +
                        "myvs_13a=${user.myvs_13a}&" +
                        "myvs_13b=${user.myvs_13b}&" +
                        "myvs_13c=${user.myvs_13c}&" +
                        "myvs_14=${user.myvs_14}" +
                        "jingdu=${user.jingdu}" +
                        "weidu=${user.weidu}" +
                        "memo22=${user.memo22}" +
                        "&did=2&day6=b&men6=a&" +
                        "ptopid=${ptopid}&" +
                        "sid=${sid}"
            )
            DataManager.addLog(ans.replace("/<[^>]+>/ig".toRegex(), ""))
            if ("已通过".toRegex().find(ans) != null || "感谢".toRegex()
                    .find(ans) != null
            ) DataManager.addLog("${Utils().getTime()} sign in user ${user.username} successfully")
            else DataManager.addLog("${Utils().getTime()} sign in user ${user.username} failed")
//            Snackbar.make(myActivity.findViewById<RecyclerView>(R.id.recycler), user.username+"签到成功", Snackbar.LENGTH_SHORT).show()
        } catch (e: Exception) {
            DataManager.addLog("${Utils().getTime()} sign in user ${user.username} failed")
//            Snackbar.make(myActivity.findViewById<RecyclerView>(R.id.recycler), e.toString(), Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun workAllUsers() {
        val users = DataManager.getData().users
        for (user in users) {
            doInBackground(user)
        }
    }

    override fun onHandleWork(intent: Intent) {
        workAllUsers()
    }
//    fun init(activity: MainActivity) {
//        myActivity = activity
//    }

}
