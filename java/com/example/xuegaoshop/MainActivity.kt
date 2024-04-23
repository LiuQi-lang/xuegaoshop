package com.example.xuegaoshop

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var user: EditText? = null
    private var passwd: EditText? = null
    private var log: Button? = null
    private var quit: Button? = null
    private var logon: Button? = null
    private var send01: String? = null
    private var send02: String? = null
    private var right: ImageView? = null
    private var right_num = 0 //设置监听事件，看是否登录成功
    private val shar: SharedPreferences? = null
    private val sp: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log = findViewById(R.id.log)
        quit = findViewById(R.id.quit)
        logon = findViewById(R.id.logon)
        user = findViewById(R.id.user)
        passwd = findViewById(R.id.passwd)
        right = findViewById(R.id.right)


        //点击注册按钮
        logon?.setOnClickListener(View.OnClickListener { //转跳到注册界面Main2Activity
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        })

        //点击登录按钮发生的事件
        log?.setOnClickListener(View.OnClickListener { //创建初始用户
            val login = getSharedPreferences("diancan", MODE_PRIVATE)
            val editor = login.edit()
            editor.putString("Root", "00000000000")
            editor.commit()
            send01 = user?.getText().toString()
            send02 = passwd?.getText().toString()
            //用正则表达式以判断用户输入的用户名是否符合规则
            val regex = Regex("[a-zA-Z]{4,}")
            //对输入的用户名和密码格式进行检验
            if (TextUtils.isEmpty(send01)) {
                Toast.makeText(this@MainActivity, "用户名不能为空", Toast.LENGTH_SHORT).show()
            } else if (!send01!!.matches(regex)) {
                Toast.makeText(this@MainActivity, "请输入规范用户名", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(send02)) {
                Toast.makeText(this@MainActivity, "请输入相应密码", Toast.LENGTH_SHORT).show()
            } else if (send02!!.length != 11) {         //这里设置密码长度为6
                Toast.makeText(this@MainActivity, "请输入正确格式的密码", Toast.LENGTH_SHORT).show()
            } else {
                val shar = getSharedPreferences("diancan", MODE_PRIVATE)
                if (send02 == shar.getString(send01, "")) {
                    val intent = Intent(this@MainActivity, MainActivity3::class.java)
                    startActivity(intent)
                    Toast.makeText(this@MainActivity, "登录成功", Toast.LENGTH_SHORT).show()
                    right_num = 1
                    //登录成功后将信息传到dsh431，方便信息显示界面调用
                    val userMessage = shar.edit()
                    userMessage.putString("user_name", send01)
                    userMessage.putString("user_passwd", send02)
                    userMessage.commit()
                } else {
                    Toast.makeText(this@MainActivity, "用户名或密码不对", Toast.LENGTH_SHORT).show()
                } //调用自定义judge()函数来判断是否能成功登录
            }
            //用户名和学号置空
            user?.setText("")
            passwd?.setText("")
        })

        //点击转跳到信息界面的手指
        right?.setOnClickListener(View.OnClickListener {
            if (right_num == 1) {
                val intent = Intent(this@MainActivity, MainActivity3::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "请先登录", Toast.LENGTH_SHORT).show()
            }
        })

        //退出程序
        quit?.setOnClickListener(View.OnClickListener {
            finishAffinity()
            System.exit(0) //杀死进程，方法一
            //android.os.Process.killProcess(android.os.Process.myPid());       //杀死进程，方法二
        })
    } //当输入的用户名和学号格式正确的时候，检测该用户是否注册，密码是否正确
    // protected void judge() {
    //SharedPreferences judge= getSharedPreferences("dsh431",MODE_PRIVATE);		//设置的文件名，可以自己更改
    //SharedPreferences shar = getSharedPreferences("dsh431",MODE_PRIVATE);
    //登录界面的用户名和已注册的用户名比较，看该用户是否存在
    //    if(send02.equals( shar.getString(send01,""))){
    //       Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
    //       right_num = 1;
    //       //登录成功后将信息传到data505，方便信息显示界面调用
    //      SharedPreferences.Editor userMessage = shar.edit();
    //      userMessage.putString("user_name",send01);
    //      userMessage.putString("user_passwd",send02);
    //      userMessage.commit();
    // }else {
    //     Toast.makeText(MainActivity.this, "用户名或密码不对", Toast.LENGTH_SHORT).show();
    //  }
    //  }
}