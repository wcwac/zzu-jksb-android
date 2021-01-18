package com.wcwac.jksb

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    fun getTime(): String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA)
        return sdf.format(Date())
    }
}