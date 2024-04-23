package com.example.xuegaoshop.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xuegaoshop.*
import com.example.xuegaoshop.adapte.MyAdapter
import com.example.xuegaoshop.entity.DemoBean
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class Fragment1 : Fragment() {
    private var tvinfo: TextView? = null
    var banner: Banner? = null
    private var imageView: ImageView? = null
    private var imageView2: ImageView? = null
    private var imageView3: ImageView? = null
    private var imageView4: ImageView? = null
    private var recyclerView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var myAdapter: MyAdapter? = null
    private var list: List<DemoBean>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment1, container, false)
        tvinfo = view.findViewById<View>(R.id.textView) as TextView
        tvinfo!!.text = "首页"
        Toast.makeText(activity, "首页", Toast.LENGTH_SHORT).show()
        imageView = view.findViewById<View>(R.id.imageView2) as ImageView
        imageView2 = view.findViewById<View>(R.id.imageView3) as ImageView
        imageView3 = view.findViewById<View>(R.id.imageView4) as ImageView
        imageView4 = view.findViewById<View>(R.id.imageView5) as ImageView
        banner = view.findViewById(R.id.banner)
        recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        super.onCreate(savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.setHasFixedSize(true)
        list = data
        myAdapter = MyAdapter(context!!)
        myAdapter!!.list = list as MutableList<DemoBean>?
        recyclerView!!.adapter = myAdapter
        val imgs = ArrayList<Int?>()
        imgs.add(R.drawable.lb)
        imgs.add(R.drawable.fm2)
        imgs.add(R.drawable.a9)
        val title = ArrayList<String>()
        title.add("点吧入驻你的手机啦！")
        title.add("招聘")
        title.add("雪糕")
        banner?.setImages(imgs)
        banner?.setImageLoader(ImageLoadBanner())
        banner?.setBannerTitles(title)
        banner?.setDelayTime(2000)
        banner?.isAutoPlay(true)
        banner?.setIndicatorGravity(BannerConfig.CENTER)
        banner?.setBannerAnimation(Transformer.Accordion)
        banner?.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        banner?.start()
        imageView!!.setOnClickListener {
            val intent = Intent(activity, picture1::class.java)
            startActivity(intent)
        }
        imageView2!!.setOnClickListener {
            val intent = Intent(activity, picture2::class.java)
            startActivity(intent)
        }
        imageView3!!.setOnClickListener {
            val intent = Intent(activity, picture3::class.java)
            startActivity(intent)
        }
        imageView4!!.setOnClickListener {
            val intent = Intent(activity, picture4::class.java)
            startActivity(intent)
        }
        return view
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

    internal inner class ImageLoadBanner : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            imageView.setImageResource(path.toString().toInt())
        }
    }
}