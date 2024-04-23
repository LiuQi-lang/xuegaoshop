package com.example.xuegaoshop

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xuegaoshop.adapte.MyAdapter
import com.example.xuegaoshop.entity.DemoBean

class Recyclerview : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var myAdapter: MyAdapter? = null
    private var list: List<DemoBean>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        init()
    }

    private fun init() {
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.setHasFixedSize(true)
        list = data
        myAdapter = MyAdapter(this)
        myAdapter!!.list = list as MutableList<DemoBean>?
        recyclerView!!.adapter = myAdapter
    }

    //1
    //2
    //3
    //4
    //5
    //6
    private val data: List<DemoBean>
        private get() {
            val list: MutableList<DemoBean> = ArrayList()
            var demoBean: DemoBean
            //1
            demoBean = DemoBean()
            demoBean.articleId = 1
            demoBean.title = "雪碧味道 "
            demoBean.description = "  雪莲"
            demoBean.time = "¥20"
            demoBean.imgid = R.drawable.a1
            list.add(demoBean)
            //2
            demoBean = DemoBean()
            demoBean.articleId = 2
            demoBean.title = "红枣与牛奶的结合"
            demoBean.description = "  红枣牛奶雪糕"
            demoBean.time = "¥18"
            demoBean.imgid = R.drawable.a3
            list.add(demoBean)
            //3
            demoBean = DemoBean()
            demoBean.articleId = 3
            demoBean.title = "红豆糯米"
            demoBean.description = "  方糕"
            demoBean.time = "¥10"
            demoBean.imgid = R.drawable.a2
            list.add(demoBean)
            //4
            demoBean = DemoBean()
            demoBean.articleId = 4
            demoBean.title = "巧克力与牛奶的结合"
            demoBean.description = "  巧乐兹"
            demoBean.time = "¥40"
            demoBean.imgid = R.drawable.a4
            list.add(demoBean)
            //5
            demoBean = DemoBean()
            demoBean.articleId = 5
            demoBean.title = "菠萝的清香与牛奶结合"
            demoBean.description = "  菠萝牛奶雪糕"
            demoBean.time = "¥12"
            demoBean.imgid = R.drawable.a5
            list.add(demoBean)
            //6
            demoBean = DemoBean()
            demoBean.articleId = 6
            demoBean.title = "菠萝味外壳和牛奶内心、"
            demoBean.description = "  大冰桶"
            demoBean.time = "¥22"
            demoBean.imgid = R.drawable.a6
            list.add(demoBean)
            return list
        }
}