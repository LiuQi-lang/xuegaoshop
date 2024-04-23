package com.example.xuegaoshop.model

import com.example.xuegaoshop.bean.WeatherResult
import com.example.xuegaoshop.iface.WeatherIface
import com.example.xuegaoshop.listener.WeatherListener
import com.example.xuegaoshop.service.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherModel : WeatherIface {
    private var service: WeatherService? = null
    private val retrofit: Retrofit

    init {
        //Retrofit 使用-1
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) //主机地址
            .addConverterFactory(GsonConverterFactory.create()) //解析方式
            .build()
    }

    override fun getWeather(cityNumber: String?, listener: WeatherListener?) {
        //Retrofit使用-2
        service = retrofit.create(WeatherService::class.java)
        val call = service?.getResult(cityNumber)
        //Retrofit使用-3
        call?.enqueue(object : Callback<WeatherResult?> {
            override fun onResponse(
                call: Call<WeatherResult?>,
                response: Response<WeatherResult?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    //调用监听事件接口的onResponse方法
                    listener!!.onResponse(response.body()!!.weatherInfo)
                } else {
                    listener!!.onFail("解析错误！")
                }
            }

            override fun onFailure(call: Call<WeatherResult?>, t: Throwable) {
                //调用监听事件接口的onFail方法
                listener!!.onFail(t.toString())
                println("----+t.toString( )=$t")
            }
        })
    }

    companion object {
        private const val BASE_URL = "http://www.weather.com.cn/" //主机地址
    }
}