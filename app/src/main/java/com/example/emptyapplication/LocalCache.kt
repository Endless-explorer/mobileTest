package com.example.emptyapplication

import android.content.Context
import android.content.SharedPreferences

class LocalCache(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("booking_cache", Context.MODE_PRIVATE)

    fun saveBookingData(json: String) {
        sharedPreferences.edit().putString("booking_data", json).putLong("timestamp", System.currentTimeMillis()).apply()
    }

    fun getBookingData(): String? {
        return sharedPreferences.getString("booking_data", null)
    }

    fun isDataExpired(): Boolean {
        val timestamp = sharedPreferences.getLong("timestamp", 0L)
        val fiveMinutesInMillis = 5 * 60 * 1000
        return (System.currentTimeMillis() - timestamp) > fiveMinutesInMillis
    }
}

