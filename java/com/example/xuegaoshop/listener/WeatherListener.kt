package com.example.xuegaoshop.listener

import com.example.xuegaoshop.bean.WeatherInfo

interface WeatherListener {
    fun onResponse(weather: WeatherInfo?)
    fun onFail(msg: String?)
}