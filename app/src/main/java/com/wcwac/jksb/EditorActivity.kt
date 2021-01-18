package com.wcwac.jksb

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ScrollView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.wcwac.jksb.DataManager.insertData


class EditorActivity : BaseActivity() {
    private var position = 1
    private lateinit var dataSet: UsersType
    private lateinit var scrollView: ScrollView
    private lateinit var username: TextView
    private lateinit var password: TextView
    private lateinit var myvs_1: TextView
    private lateinit var myvs_2: TextView
    private lateinit var myvs_3: TextView
    private lateinit var myvs_4: TextView
    private lateinit var myvs_13a: TextView
    private lateinit var myvs_13b: TextView
    private lateinit var myvs_13c: TextView
    private lateinit var myvs_14: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        scrollView = findViewById(R.id.scrollview)
        username = findViewById(R.id.adduser_username)
        password = findViewById(R.id.adduser_password)
        myvs_1 = findViewById(R.id.adduser_myvs_1)
        myvs_2 = findViewById(R.id.adduser_myvs_2)
        myvs_3 = findViewById(R.id.adduser_myvs_3)
        myvs_4 = findViewById(R.id.adduser_myvs_4)
        myvs_13a = findViewById(R.id.adduser_myvs_13a)
        myvs_13b = findViewById(R.id.adduser_myvs_13b)
        myvs_13c = findViewById(R.id.adduser_myvs_13c)
        myvs_14 = findViewById(R.id.adduser_myvs_14)

        dataSet = DataManager.getData()
        position = intent.getIntExtra("position", -1)

        if (position >= 0) {
            fillData(dataSet.users[position])
        } else {
            clearServer()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editor, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun clearServer() {
        username.text = ""
        password.text = ""
        myvs_1.text = ""
        myvs_2.text = ""
        myvs_3.text = ""
        myvs_4.text = ""
        myvs_13a.text = ""
        myvs_13b.text = ""
        myvs_13c.text = ""
        myvs_14.text = ""
    }

    private fun fillData(user: UsersType.User) {
        title = user.username
        username.text = user.username
        password.text = user.password
        myvs_1.text = user.myvs_1
        myvs_2.text = user.myvs_2
        myvs_3.text = user.myvs_3
        myvs_4.text = user.myvs_4
        myvs_13a.text = user.myvs_13a
        myvs_13b.text = user.myvs_13b
        myvs_13c.text = user.myvs_13c
        myvs_14.text = user.myvs_14
    }

    private fun saveData() {
        if (username.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (username.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (myvs_1.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (myvs_2.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (myvs_3.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (myvs_4.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (myvs_13a.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (myvs_13b.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (myvs_13c.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (myvs_14.text.isEmpty()) {
            Snackbar.make(scrollView, "信息不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        val user = UsersType.User()
        user.username = username.text.toString()
        user.password = password.text.toString()
        user.myvs_1 = myvs_1.text.toString()
        user.myvs_2 = myvs_2.text.toString()
        user.myvs_3 = myvs_3.text.toString()
        user.myvs_4 = myvs_4.text.toString()
        user.myvs_13a = myvs_13a.text.toString()
        user.myvs_13b = myvs_13b.text.toString()
        user.myvs_13c = myvs_13c.text.toString()
        user.myvs_14 = myvs_14.text.toString()
        insertData(user, position)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save_user -> {
            saveData()
            true
        }
        else -> {
            finish()
            true
        }
    }


}
