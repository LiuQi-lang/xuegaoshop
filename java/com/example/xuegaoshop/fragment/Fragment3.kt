package com.example.xuegaoshop.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.xuegaoshop.R
import com.example.xuegaoshop.Shouquan
import com.example.xuegaoshop.WeatherActivity

class Fragment3 : Fragment() {
    private var tvinfo3: TextView? = null
    private var button: Button? = null
    private var baidu: ImageView? = null
    private var phone: ImageView? = null
    private var dizhi: ImageView? = null
    private var tianqi: ImageView? = null
    private var erweima: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment3, container, false)
        tvinfo3 = view.findViewById<View>(R.id.textView3) as TextView
        tvinfo3!!.text = "设置"
        Toast.makeText(activity, "设置", Toast.LENGTH_SHORT).show()
        button = view.findViewById<View>(R.id.button2) as Button
        baidu = view.findViewById<View>(R.id.imageView15) as ImageView
        phone = view.findViewById<View>(R.id.imageView17) as ImageView
        dizhi = view.findViewById<View>(R.id.imageView16) as ImageView
        tianqi = view.findViewById<View>(R.id.imageView19) as ImageView
        erweima = view.findViewById<View>(R.id.imageView18) as ImageView
        button!!.setOnClickListener {
            val intent = Intent(activity, Shouquan::class.java)
            startActivity(intent)
        }
        erweima!!.setOnClickListener {
            val intent = Intent(activity, erweima!!::class.java)
            startActivity(intent)
        }
        tianqi!!.setOnClickListener {
            val intent = Intent(activity, WeatherActivity::class.java)
            startActivity(intent)
        }
        val uri = Uri.parse("http://www.baidu.com")
        baidu!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        phone!!.setOnClickListener {
            val intent2 = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 88888888))
            startActivity(intent2)
        }
        val uri2 =
            Uri.parse("https://ditu.amap.com/search?id=B033202A4L&city=511702&geoobj=107.525112%7C31.23271%7C107.811532%7C31.362951&query_type=IDQ&query=%E5%9B%9B%E5%B7%9D%E6%96%87%E7%90%86%E5%AD%A6%E9%99%A2(%E8%8E%B2%E6%B9%96%E6%A0%A1%E5%8C%BA)&zoom=12.04")
        dizhi!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, uri2)
            startActivity(intent)
        }
        return view
    }
}