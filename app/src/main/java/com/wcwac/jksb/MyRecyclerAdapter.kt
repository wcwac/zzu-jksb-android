package com.wcwac.jksb

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter(activity: MainActivity) :
        RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    private lateinit var dataSet: UsersType
    private var myActivity: MainActivity = activity

    fun update() {
        dataSet = DataManager.getData()
        notifyDataSetChanged()
    }

    init {
        update()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.recycler_item, parent, false)
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout_text = itemView.findViewById<TextView>(R.id.layout_text)!!//layout_text!!
        val layout_edit = itemView.findViewById<ImageView>(R.id.layout_edit)!!//layout_edit!!
        val layout_remove = itemView.findViewById<ImageView>(R.id.layout_remove)!!//layout_remove!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layout_text.text = dataSet.users[position].username

        holder.layout_edit.setOnClickListener {
            val intent = Intent().putExtra("position", position)
            myActivity.startActivity(intent.setClass(myActivity, EditorActivity::class.java))
            update()
        }
        holder.layout_remove.setOnClickListener {
            AlertDialog.Builder(myActivity).setTitle("系统提示")
                    .setMessage("确认删除吗？")
                    .setPositiveButton("确定") { _, _ ->
                        if (DataManager.removeData(position) == 0) {
                            update()
                            notifyItemRemoved(position)
                        }
                    }.setNegativeButton("返回") { _, _ ->
                    }.show()
        }
    }

    override fun getItemCount() = dataSet.users.size

}

