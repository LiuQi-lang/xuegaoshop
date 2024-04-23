package com.example.xuegaoshop.iface

import com.example.xuegaoshop.listener.WeatherListener

interface WeatherIface {
    fun getWeather(cityNumber: String?, listener: WeatherListener?)
}