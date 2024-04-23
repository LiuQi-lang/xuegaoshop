package com.example.xuegaoshop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.xuegaoshop.adapter1.PersonApater
import com.example.xuegaoshop.entity1.Person

class MainActivity3 : AppCompatActivity() {
    private var userName: TextView? = null
    private var userMessage: TextView? = null
    private var del: TextView? = null
    private var fan //返回按钮
            : ImageButton? = null
    private var go: Button? = null
    private var lb1: ListView? = null
    private var myAdapter: PersonApater? = null
    private var ImageView: ImageView? = null
    private var del_namber = 0
    private val imgs = intArrayOf(R.drawable.f1, R.drawable.f2)
    private val pdata: MutableList<Person> = ArrayList()
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        ImageView = findViewById<View>(R.id.go_back) as ImageView
        //ImageView.setImageResource(R.drawable.waiting);
        userName = findViewById(R.id.userName)
        userMessage = findViewById(R.id.userMessage)
        fan = findViewById(R.id.go_back)
        del = findViewById(R.id.del_user)
        go = findViewById(R.id.go)
        lb1 = findViewById<View>(R.id.lb1) as ListView
        myAdapter = PersonApater(pdata, this@MainActivity3)
        lb1 = findViewById<View>(R.id.lb1) as ListView
        initDataBooks()
        myAdapter = PersonApater(pdata, this@MainActivity3)
        lb1!!.adapter = myAdapter
        //        lb1.setOnItemClickListener((AdapterView.OnItemClickListener) this);
//        lb1.setOnScrollListener((AbsListView.OnScrollListener) this);
        val sharedPreferences = getSharedPreferences("dsh431", MODE_PRIVATE)
        val user_name = sharedPreferences.getString("user_name", "")
        val user_password = sharedPreferences.getString("user_passwd", "")
        val get_user = sharedPreferences.getInt("countNum", 0)
        del_namber = sharedPreferences.getInt("del_user", 0)
        userName?.setText(user_name)
        //判断是否为管理员用户，设置显示信息
        if (user_name == "Root") {
            userMessage?.setText(
                """
    亲爱的管理员${user_name}您好！
    您的密码为$user_password
    共有${get_user}人注册过用户
    共有${del_namber}注销用户
    """.trimIndent()
            )
        } else {
            userMessage?.setText(
                """
    尊敬的${user_name}客户您好！
    您的密码为${user_password}请牢记！
    """.trimIndent()
            )
        }


        //点击返回按钮发生事件
        fan?.setOnClickListener(View.OnClickListener {
            val i = Intent(this@MainActivity3, MainActivity::class.java)
            startActivity(i)
        })
        //点击进入
        go?.setOnClickListener(View.OnClickListener {
            val i = Intent(this@MainActivity3, MainActivity4::class.java)
            startActivity(i)
        })

        //点击删除用户发生时间
        del?.setOnClickListener(View.OnClickListener {
            //判断该用户是否为可注销用户
            if (user_name == "Root") {
                Toast.makeText(this@MainActivity3, "该用户不允许被注销", Toast.LENGTH_SHORT).show()
            } else {
                //弹框设置
                val builder = AlertDialog.Builder(this@MainActivity3)
                builder.setMessage("$user_name,您确定要注销用户吗？")
                builder.setNegativeButton("确定") { dialog, which ->
                    val editor = sharedPreferences.edit()
                    editor.remove(user_name)
                    editor.putInt("del_user", ++del_namber)
                    editor.commit()
                    val i = Intent(this@MainActivity3, MainActivity::class.java)
                    startActivity(i)
                }
                builder.setPositiveButton("取消") { dialog, which -> }
                val dialog = builder.create()
                dialog.show()
            }
        })
    }

    private fun initDataBooks() {
        for (i in imgs.indices) {
            val person = Person(imgs[i])
            pdata.add(person)
        }
    }
}