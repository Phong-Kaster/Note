package com.example.note.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.abs

object DateUtil {
    /**
     * HOUR FORMAT - https://www.digitalocean.com/community/tutorials/java-simpledateformat-java-date-format
     * K -> Hour in am/pm for 12 hour format (0-11)
     * H -> Hour in the day (0-23)
     * h -> Hour in am/pm for 12 hour format (1-12)
     * */
    const val PATTERN_HH_mm = "HH:mm" // 09:15
    const val PATTERN_HH_mm_ss = "HH:mm:ss" // 09:15:45
    const val PATTERN_hh_mm_ss = "hh:mm:ss" // 09:15:45
    const val PATTERN_hh_mm_aa = "hh:mm aa" // 09:15 AM
    const val PATTERN_hh_mm_ss_aa = "KK:mm:ss aa" // 09:15:50 AM

    // DAY FORMAT
    const val PATTERN_EEE_MMM_dd = "EEE, MMM dd" // Mon, December 01
    const val PATTERN_EEEE = "EEEE" // Monday
    const val PATTERN_EEE = "EEE" // Mon
    const val PATTERN_YYYY = "YYYY" // 2024
    const val PATTERN_dd_MMM = "dd MMM" // 14 DEC
    const val PATTERN_MMM = "MMM" // 14 DEC
    const val PATTERN_dd = "dd" // 14
    const val PATTERN_EEE_MMM_dd_hh_mm_aa = "EEE, MMMM YYYY dd hh:mm aa" // Mon, December 01


    @SuppressLint("SimpleDateFormat")
    fun Date.formatWithPattern(pattern: String, locale: Locale = Locale.getDefault()): String {
        val simpleDateFormat = SimpleDateFormat(pattern, locale)
        return simpleDateFormat.format(this@formatWithPattern)
    }

    /**
     * convert from seconds to hours
     * for example: 18960 seconds = 5 hour 15 minute 30 second
     * @return hour
     */
    fun Int.toHour(): Int {
        val outcome = this / (60 * 60)
        return outcome
    }

    /**
     * convert from seconds to hours
     * for example: 18960 seconds = 5 hour 15 minute 30 second
     * @return minute
     */
    fun Int.toMinute(): Int {
        val leftSecond = this - this.toHour() * 60 * 60
        val outcome = leftSecond / 60
        return outcome
    }

    /**
     * convert from seconds to hours
     * for example: 18960 seconds = 5 hour 15 minute 30 second
     * @return second
     */
    fun Int.toSecond(): Int {
        return this - this.toHour() * 60 * 60 - this.toMinute() * 60
    }
}