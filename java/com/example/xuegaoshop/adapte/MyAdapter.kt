package com.example.xuegaoshop.adapte

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.xuegaoshop.DetaiActivity
import com.example.xuegaoshop.R
import com.example.xuegaoshop.entity.DemoBean

class MyAdapter(  //上下文
    var context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var layoutInflater //动态加载布局
            : LayoutInflater

    //自定义:传进数据源
    var list : MutableList<DemoBean>? = ArrayList()//保存要显示的数据

    //
    init {
        layoutInflater = LayoutInflater.from(context)
    }

    //
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var tvtitle: TextView
        var tvdescript: TextView
        var tvtime: TextView
        var button: Button

        init {
            imageView = itemView.findViewById<View>(R.id.iv_adapter_list_pic) as ImageView
            tvtitle = itemView.findViewById<View>(R.id.tv_color_size) as TextView
            tvdescript = itemView.findViewById<View>(R.id.tv_intro) as TextView
            tvtime = itemView.findViewById<View>(R.id.tv_price) as TextView
            button = itemView.findViewById<View>(R.id.bt0) as Button
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item2, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val demoBean = list!![position] ?: return
        val holder1 = holder as ViewHolder
        holder1.imageView.setImageResource(demoBean.imgid)
        holder1.tvtitle.text = demoBean.title
        holder1.tvdescript.text = demoBean.description
        holder1.tvtime.text = demoBean.time
        holder1.button.setOnClickListener {
            list!!.removeAt(position)
            notifyDataSetChanged()
        }
        holder1.itemView.setOnClickListener {
            val strid = list!![position].articleId.toString()
            Toast.makeText(context, "" + strid, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DetaiActivity::class.java)
            val demoBean = list!![position]
            intent.putExtra("data", demoBean)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}