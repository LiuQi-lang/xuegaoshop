package com.example.xuegaoshop

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.xuegaoshop.adapter.AdapterFragmentPager
import com.example.xuegaoshop.fragment.Fragment1
import com.example.xuegaoshop.fragment.Fragment2
import com.example.xuegaoshop.fragment.Fragment3

class MainActivity4 : AppCompatActivity() {
    private var layout1: LinearLayout? = null
    private var layout2: LinearLayout? = null
    private var layout3 //用于界面切换
            : LinearLayout? = null
    private var img1: ImageView? = null
    private var img2: ImageView? = null
    private var img3 //用于设置图片
            : ImageView? = null
    private var vp //用于滑动切换
            : ViewPager2? = null
    private var listfragment //Fragment list
            : MutableList<Fragment>? = null
    private var mAdapter //适配器
            : AdapterFragmentPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        init()
        initFragment()
    }

    private fun init() {
        img1 = findViewById<View>(R.id.img1) as ImageView
        img2 = findViewById<View>(R.id.img2) as ImageView
        img3 = findViewById<View>(R.id.img3) as ImageView
        layout1 = findViewById<View>(R.id.layout1) as LinearLayout
        layout2 = findViewById<View>(R.id.layout2) as LinearLayout
        layout3 = findViewById<View>(R.id.layout3) as LinearLayout

        //layout点击事件
        layout1!!.setOnClickListener(onClickListener)
        layout2!!.setOnClickListener(onClickListener)
        layout3!!.setOnClickListener(onClickListener)
    }

    private fun reseImage() {
        img1!!.setImageResource(R.drawable.shouye1)
        img2!!.setImageResource(R.drawable.dingdan1)
        img3!!.setImageResource(R.drawable.my1)
    }

    private fun selectTab(i: Int) {
        when (i) {
            0 -> img1!!.setImageResource(R.drawable.shouye2)
            1 -> img2!!.setImageResource(R.drawable.dingdan2)
            2 -> img3!!.setImageResource(R.drawable.my2)
        }
        vp!!.currentItem = i
    }

    var onClickListener = View.OnClickListener { view -> //先选四个按钮都设为未选图片
        reseImage()
        when (view.id) {
            R.id.layout1 -> {
                Toast.makeText(this@MainActivity4, "切换底部栏首页", Toast.LENGTH_SHORT).show()
                selectTab(0)
            }
            R.id.layout2 -> {
                Toast.makeText(this@MainActivity4, "切换底部栏记账本", Toast.LENGTH_SHORT).show()
                selectTab(1)
            }
            R.id.layout3 -> {
                Toast.makeText(this@MainActivity4, "切换底部栏设置", Toast.LENGTH_SHORT).show()
                selectTab(2)
            }
        }
    }

    //初始化Fragment相关
    private fun initFragment() {
        listfragment = ArrayList() //实例化Fragment list
        //将Fragment加入到list中
        listfragment?.add(Fragment1())
        listfragment?.add(Fragment2())
        listfragment?.add(Fragment3())
        vp = findViewById<View>(R.id.viewPager) as ViewPager2
        mAdapter = AdapterFragmentPager(this, listfragment as ArrayList<Fragment>)
        vp!!.adapter = mAdapter
        vp!!.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                reseImage()
                selectTab(position)
            }
        })
    }
}