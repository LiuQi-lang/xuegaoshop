package com.example.xuegaoshop

import android.content.ContentValues
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class new_cost : AppCompatActivity() {
    private var helper: DBHelper? = null
    private var et_cost_title: EditText? = null
    private var et_cost_money: EditText? = null
    private var dp_cost_date: DatePicker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_cost)
        initView()
    }

    private fun initView() {
        helper = DBHelper(this@new_cost)
        et_cost_title = findViewById(R.id.et_cost_title)
        et_cost_money = findViewById(R.id.et_cost_money)
        dp_cost_date = findViewById(R.id.dp_cost_date)
    }

    fun okButton(view: View?) {
        val titleStr = et_cost_title!!.text.toString().trim { it <= ' ' }
        val moneyStr = et_cost_money!!.text.toString().trim { it <= ' ' }
        val dateStr = (dp_cost_date!!.year.toString() + "-" + (dp_cost_date!!.month + 1) + "-"
                + dp_cost_date!!.dayOfMonth) //这里getMonth会比当前月份少一个月，所以要+1
        if ("" == moneyStr) { //可以不填写Title但是不能不填金额
            val toast = Toast.makeText(this, "请填写金额", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else {
            val db = helper!!.writableDatabase
            val values = ContentValues()
            values.put("Title", titleStr)
            values.put("Money", moneyStr)
            values.put("Date", dateStr)
            val account = db.insert("account", null, values)
            if (account > 0) {
                val toast = Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                setResult(1)
                finish()
            } else {
                val toast = Toast.makeText(this, "请重试", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                db.close()
            }
            setResult(1)
            finish()
        }
    }
}