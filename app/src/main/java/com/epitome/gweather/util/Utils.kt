package com.epitome.gweather.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun baseLogger(title: String, value: Any) {
        Log.e(title, ":::> $value")
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDate(passedDate: String): String {

        val inputDate = passedDate.replace("T", " ").replace("Z","")
        return try {
            SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(inputDate).toString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            inputDate
        }
    }
}