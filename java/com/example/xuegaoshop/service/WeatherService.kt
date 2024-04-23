package com.example.xuegaoshop.service

import com.example.xuegaoshop.bean.WeatherResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    //将接口的URL补充完整
    @GET("data/sk/{cityNumber}.html")
    fun  //向API传参数
            getResult(@Path("cityNumber") cityNumber: String?): Call<WeatherResult?>?
}