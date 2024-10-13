package com.example.emptyapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var dataManager: DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 创建 DataManager 并启用 mock 数据
        dataManager = DataManager(this, isMock = true)

        // 获取数据并打印
        dataManager.getBookingData { booking ->
            if (booking != null) {
                Log.d("BookingActivity", "onCreate Received Booking Data: $booking")
            } else {
                Log.d("BookingActivity", "Failed to get Booking Data")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // 页面回到前台时再次获取数据
        dataManager.getBookingData { booking ->
            if (booking != null) {
                Log.d("BookingActivity", "onResume Received Booking Data: $booking")
            } else {
                Log.d("BookingActivity", "Failed to get Booking Data")
            }
        }
    }
}

