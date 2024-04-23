package com.example.xuegaoshop.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ListView
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.xuegaoshop.*

class Fragment2 : Fragment() {
    private var helper: DBHelper? = null
    private var listView: ListView? = null
    private var Add: ImageButton? = null
    private var list: MutableList<costList>? = null
    private val videoView: VideoView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment2, container, false)
        super.onCreate(savedInstanceState)
        helper = DBHelper(context)
        listView = view.findViewById(R.id.list_view)
        val videoView = view.findViewById<VideoView>(R.id.videoView)
        //加载指定的视频文件
        val path = Environment.getExternalStorageDirectory().path + "/chi.mp4"
        videoView.setVideoPath(path)

        //创建MediaController对象
        val mediaController = MediaController(context)

        //VideoView与MediaController建立关联
        videoView.setMediaController(mediaController)

        //让VideoView获取焦点
        videoView.requestFocus()
        Add = view.findViewById(R.id.add)
        Add?.setOnClickListener(View.OnClickListener {
            //转跳到登录界面MainActivity
            val i = Intent(activity, new_cost::class.java)
            startActivity(i)
            //finish();
        })
        val intent = Intent(activity, new_cost::class.java)
        startActivityForResult(intent, 1)
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == 1) {
            list = ArrayList()
            val db = helper!!.readableDatabase
            val cursor = db.query(
                "account", null, null, null, null,
                null, null
            )
            while (cursor.moveToNext()) {
                val clist = costList() //构造实例
                clist.id = cursor.getString(cursor.getColumnIndexOrThrow("_id"))
                clist.title = cursor.getString(cursor.getColumnIndexOrThrow("Title"))
                clist.date = cursor.getString(cursor.getColumnIndexOrThrow("Date"))
                clist.money = cursor.getString(cursor.getColumnIndexOrThrow("Money"))
                (list as ArrayList<costList>).add(clist)
            }
            //绑定适配器
            listView!!.adapter = ListAdapter(context, list as ArrayList<costList>)
            db.close()
        }
    }
}