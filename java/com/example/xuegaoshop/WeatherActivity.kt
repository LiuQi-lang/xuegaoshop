package com.example.xuegaoshop

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.xuegaoshop.bean.WeatherInfo
import com.example.xuegaoshop.listener.WeatherListener
import com.example.xuegaoshop.model.WeatherModel

class WeatherActivity : AppCompatActivity(), View.OnClickListener, WeatherListener {
    private var cityNOInput: EditText? = null
    private var city: TextView? = null
    private var cityNO: TextView? = null
    private var temp: TextView? = null
    private var wd: TextView? = null
    private var ws: TextView? = null
    private var sd: TextView? = null
    private var loadingWeather: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather)
        cityNOInput = findViewById<View>(R.id.et_city_no) as EditText
        city = findViewById<View>(R.id.tv_city) as TextView
        cityNO = findViewById<View>(R.id.tv_city_no) as TextView
        temp = findViewById<View>(R.id.tv_temp) as TextView
        wd = findViewById<View>(R.id.tv_WD) as TextView
        ws = findViewById<View>(R.id.tv_WS) as TextView
        sd = findViewById<View>(R.id.tv_SD) as TextView
        //实例化进度条
        loadingWeather = ProgressDialog(this)
        loadingWeather!!.setTitle("查询中...")
        //获取按钮控件并设置按钮的点击事件监听器
        findViewById<View>(R.id.but).setOnClickListener(this)
        findViewById<View>(R.id.button2).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        //city.setText("查询中....);
        //.数据加载进度条
        loadingWeather!!.show()
        when (view.id) {
            R.id.but -> {
                //普通创建对象方式
                val weatherModel = WeatherModel()
                weatherModel.getWeather(cityNOInput!!.text.toString(), this)
            }
        }
    }

    override fun onResponse(weather: WeatherInfo?) {
        //取消数据加载进度条
        loadingWeather!!.dismiss()
        //显示获得的天气数据
        if (weather != null) {
            city!!.text = weather.city
            cityNO!!.text = weather.cityid
            temp!!.text = weather.temp
            wd!!.text = weather.wD
            ws!!.text = weather.wS
            sd!!.text = weather.sD
        } else {
            city!!.text = "未知"
        }
    }

    override fun onFail(msg: String?) {
        //取消数据加载进度条
        loadingWeather!!.dismiss()
        city!!.text = msg
        println("----+msg1$msg")
    }
    /***  @Override
     * public void onPointerCaptureChanged(boolean hasCapture) {
     * }
     */
}