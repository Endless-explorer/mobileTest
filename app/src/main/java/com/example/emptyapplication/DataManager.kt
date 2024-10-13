package com.example.emptyapplication

import android.content.Context
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataManager(private val context: Context, private val isMock: Boolean = false) {

    private val localCache = LocalCache(context)
    private val bookingService: BookingService
    private val gson = Gson()
    private val url ="https://api.example.com/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)  // 替换为真实的API地址
            .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 转换器
            .build()

        bookingService = retrofit.create(BookingService::class.java)
    }

    // 对外提供的统一接口
    fun getBookingData(callback: (Booking?) -> Unit) {
        val cachedData = localCache.getBookingData()
        if (cachedData != null) {
            val booking = gson.fromJson(cachedData, Booking::class.java)
            callback(booking)

            if (localCache.isDataExpired()) {
                refreshBookingData(callback)
            }
        } else {
            refreshBookingData(callback)
        }
    }

    // 刷新数据
    private fun refreshBookingData(callback: (Booking?) -> Unit) {
        if (isMock) {
            // 使用 Mock 数据
            val mockJson = MockJsonUtil.loadJSONFromAsset(context, "booking.json")
            if (mockJson != null) {
                val booking = gson.fromJson(mockJson, Booking::class.java)
                localCache.saveBookingData(mockJson)
                callback(booking)
            } else {
                callback(null)
            }
        } else {
            // 正常的网络请求
            bookingService.getBooking().enqueue(object : Callback<Booking> {
                override fun onResponse(call: Call<Booking>, response: Response<Booking>) {
                    if (response.isSuccessful && response.body() != null) {
                        val newBookingData = gson.toJson(response.body())
                        localCache.saveBookingData(newBookingData)
                        callback(response.body())
                    } else {
                        callback(null) // 错误处理
                    }
                }

                override fun onFailure(call: Call<Booking>, t: Throwable) {
                    callback(null)  // 错误处理
                }
            })
        }
    }
}

