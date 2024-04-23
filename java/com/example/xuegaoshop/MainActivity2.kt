package com.example.xuegaoshop

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.provider.MediaStore
import android.text.TextUtils
import android.text.format.Time
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.UriUtils
import com.bumptech.glide.Glide
import java.io.File
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    private var new_user: EditText? = null
    private var new_passwd: EditText? = null
    private var logon1: Button? = null
    private var back: Button? = null
    var re_send1: String? = null
    var re_send2: String? = null

    //定义对象
    var img_camer: ImageView? = null
    var btn_camer: Button? = null
    var btn_photo: Button? = null

    //定义一个路径：1 拍照的临时路径 2 显示照片的最终luj
    var tmp_path: String? = null
    var disp_path: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val builder = VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
        }
        new_user = findViewById(R.id.new_user)
        new_passwd = findViewById(R.id.new_passwd)
        logon1 = findViewById(R.id.logon1)
        back = findViewById(R.id.back)
        //第一步，绑定控件
        initView()
        //第二步，拍照按钮点击事件
        btnCamOnclick()
        //第三步，拍完照之后，我们接收照片并显示 系统回调 打开相册， 选择照片 系统回调
        //第四步，打开相册按钮点击事件
        btnPhoOnclick()


        //点击注册按钮发生的事件
        logon1?.setOnClickListener(View.OnClickListener {
            //用正则表达式以判断用户输入的用户名是否符合规则
            val regex = Regex("[a-zA-Z]{4,}")
            re_send1 = new_user?.getText().toString()
            re_send2 = new_passwd?.getText().toString()
            //对注册的用户名和学号格式进行检验
            if (TextUtils.isEmpty(re_send1)) {
                Toast.makeText(this@MainActivity2, "请输入用户名", Toast.LENGTH_SHORT).show()
            } else if (!re_send1!!.matches(regex)) {
                Toast.makeText(this@MainActivity2, "请输入规范的用户名", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(re_send2)) {
                Toast.makeText(this@MainActivity2, "请输入相应密码", Toast.LENGTH_SHORT).show()
            } else if (re_send2!!.length != 11) {
                Toast.makeText(this@MainActivity2, "请设置11位的密码", Toast.LENGTH_SHORT).show()
            } else {
                judge_user(re_send1) //调用自定义函数来判断是否注册成功
            }
        })
        //点击返回登录按钮发生的事件
        back?.setOnClickListener(View.OnClickListener {
            //转跳到登录界面MainActivity
            val i = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(i)
            //finish();
        })
    }

    //注册新用户，将新注册的用户添加到data505
    protected fun setNew_user(countNum: Int) {
        var countNum = countNum
        val login = getSharedPreferences("diancan", MODE_PRIVATE)
        val editor = login.edit()
        editor.putString(re_send1, re_send2)
        ++countNum
        editor.putInt("countNum", countNum)
        editor.commit()
    }

    //判断新注册的用户是否存在
    protected fun judge_user(set_user: String?) {
        val shar = getSharedPreferences("diancan", MODE_PRIVATE)
        //判断该用户是否注册过
        if (re_send2 == shar.getString(re_send1, "")) {
            Toast.makeText(this@MainActivity2, "注册不成功", Toast.LENGTH_SHORT).show()
        } else {
            //获取计数count，共注册的用户数
            var count = shar.getInt("countNum", 0)
            //调用自定义函数，注册新用户
            setNew_user(count)
            Toast.makeText(
                this@MainActivity2, """
     注册成功
     您已经是第${++count}位注册此用户的人
     """.trimIndent(), Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun initView() {
        img_camer = findViewById(R.id.imageView_cam)
        //img_photo = findViewById(R.id.imageView_photo);
        btn_camer = findViewById(R.id.button_cam)
        btn_photo = findViewById(R.id.button_photo)
    }

    private fun btnCamOnclick() {
        btn_camer!!.setOnClickListener {
            Log.d(TAG, "onClick:1" + "单击了按钮")
            //SharedPreferences btn_camer= getSharedPreferences("431dsh",MODE_PRIVATE);
            //拍照代码
            //前期准备：路径2
            tmp_path =
                Environment.getExternalStorageDirectory().absolutePath + "/img_" + randFileName() + ".jpg"
            Log.d(TAG, "onClick:2$tmp_path")
            val imgfile = File(tmp_path)
            Log.d(TAG, "onClick:3$imgfile")
            try {
                if (imgfile.exists()) {
                    imgfile.delete()
                }
                imgfile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                Log.d(TAG, "onClick:333$e")
            }
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgfile))
            startActivityForResult(intent, 11)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            11 -> if (resultCode == RESULT_OK) {
                disp_path = tmp_path
                Log.d(TAG, "onClick:4$disp_path")
                print("-11--$disp_path")
                Glide.with(this@MainActivity2).load(disp_path).into(img_camer!!)
            }
            22 -> if (resultCode == RESULT_OK) {
                val imageuri = data!!.data ?: return
                print("---22-$imageuri")
                disp_path = UriUtils.uri2File(imageuri).path
                Glide.with(this@MainActivity2).load(disp_path).into(img_camer!!)
            }
            else -> {}
        }
    }

    //时间戳，为保证每次拍的照片都以不同的文件名保存到SD卡上
    private fun randFileName(): String {
        val t = Time()
        t.setToNow()
        return t.year.toString() + "" + (t.month + 1) + "" + t.monthDay + "" + t.hour + "" + t.minute + "" + t.second + ""
    }

    private fun btnPhoOnclick() {
        btn_photo!!.setOnClickListener {
            val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"
            startActivityForResult(intent, 22)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}