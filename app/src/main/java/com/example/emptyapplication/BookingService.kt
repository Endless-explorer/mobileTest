package com.example.emptyapplication

import retrofit2.http.GET
import retrofit2.Call

interface BookingService {
    @GET("/api/bookings")  // 替换为真实的 API 路径
    fun getBooking(): Call<Booking>
}
