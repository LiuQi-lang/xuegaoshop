package com.example.xuegaoshop

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.xuegaoshop.entity.DemoBean

class DetaiActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    var tvtitle: TextView? = null
    var tvdescript: TextView? = null
    var tvtime: TextView? = null
    var button: Button? = null
    var button2: Button? = null
    private var tv1: TextView? = null
    private var spinner: Spinner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item)
        imageView = findViewById<View>(R.id.imageView) as ImageView
        tvtitle = findViewById<View>(R.id.textView) as TextView
        tvdescript = findViewById<View>(R.id.textView2) as TextView
        tvtime = findViewById<View>(R.id.textView3) as TextView
        button = findViewById<View>(R.id.but) as Button
        button2 = findViewById<View>(R.id.but2) as Button
        val demoBean = intent.getSerializableExtra("data") as DemoBean?
        imageView!!.setImageResource(demoBean!!.imgid)
        tvtitle!!.text = demoBean.title
        tvdescript!!.text = demoBean.description
        tvtime!!.text = demoBean.time
        init()
        button2!!.setOnClickListener {
            val builder = AlertDialog.Builder(this@DetaiActivity)
            builder.setMessage("购买成" + "功！")
            builder.setPositiveButton("取消") { dialog, which -> }
            val dialog = builder.create()
            dialog.show()
        }
        button!!.setOnClickListener {
            val builder = AlertDialog.Builder(this@DetaiActivity)
            builder.setMessage("您确定加入购物车" + "吗？")
            builder.setNegativeButton("确定") { dialog, which -> }
            builder.setPositiveButton("取消") { dialog, which -> }
            val dialog = builder.create()
            dialog.show()
        }
    }

    fun init() {
        tv1 = findViewById<View>(R.id.tv1) as TextView
        spinner = findViewById<View>(R.id.spinner) as Spinner
        val arr = arrayOf("一只", "两只", "十支", "一箱")
        //定义适配器
        /**ArrayAdapter<String> adapter = new ArrayAdapter<String>
         * (this,R.layout.support_simple_spinner_dropdown_item,
         * getResources().getStringArray(R.array.cityname));</String></String> */
        val adapter = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item, arr
        )
        //定义适配器的样式
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        //绑定spinner
        spinner!!.adapter = adapter
        //设置提示语句
        spinner!!.prompt = "请选择口味："
        spinner!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, i: Int, l: Long) {
                val str = parent.getItemAtPosition(i).toString()
                tv1!!.text = "您选择的口味是$str"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}