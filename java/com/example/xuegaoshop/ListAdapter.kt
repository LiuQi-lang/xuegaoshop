package com.example.xuegaoshop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter : BaseAdapter {
    var mList: List<costList>

    constructor(list: List<costList>) {
        mList = list
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getItem(position: Int): Any {
        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val view = mLayoutInflater!!.inflate(R.layout.list_item, null)
        //取出数据赋值
        val item = mList[position]
        val tv_title = view.findViewById<TextView>(R.id.tv_title)
        val tv_date = view.findViewById<TextView>(R.id.tv_date)
        val tv_money = view.findViewById<TextView>(R.id.tv_money)
        //绑定
        tv_title.text = mList[position].title
        tv_date.text = mList[position].date
        tv_money.text = mList[position].money
        return view
    }

    private val getmList: List<costList>? = null
    private var mLayoutInflater: LayoutInflater? = null

    constructor(context: Context?, list: List<costList>) {
        mList = list
        //通过外部传来的Context初始化LayoutInflater对象
        mLayoutInflater = LayoutInflater.from(context)
    }
}