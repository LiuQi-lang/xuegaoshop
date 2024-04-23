package com.example.xuegaoshop.adapter1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.xuegaoshop.R
import com.example.xuegaoshop.entity1.Person

class PersonApater(pdata: MutableList<Person>, context: Context) : BaseAdapter() {
    private var pdata: MutableList<Person> = ArrayList()
    private val context: Context

    init {
        this.pdata = pdata
        this.context = context
    }

    override fun getCount(): Int {
        return pdata.size //liebiaozhongshujugeshu
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    internal class ViewHolder {
        var img: ImageView? = null
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        var view = view
        val holder: ViewHolder
        if (view == null) {
            //xinjian
            holder = ViewHolder()
            view = LayoutInflater.from(context).inflate(R.layout.activity_xvzhi, viewGroup, false)
            holder.img = view.findViewById<View>(R.id.im1) as ImageView
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        holder.img!!.setImageResource(pdata[i].img)
        return view
    }
}