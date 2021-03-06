package com.simplemobiletools.calendar.helpers

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class Config(context: Context) {
    private val mPrefs: SharedPreferences

    companion object {
        fun newInstance(context: Context) = Config(context)
    }

    init {
        mPrefs = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
    }

    var isFirstRun: Boolean
        get() = mPrefs.getBoolean(IS_FIRST_RUN, true)
        set(firstRun) = mPrefs.edit().putBoolean(IS_FIRST_RUN, firstRun).apply()

    var isDarkTheme: Boolean
        get() = mPrefs.getBoolean(IS_DARK_THEME, false)
        set(isDarkTheme) = mPrefs.edit().putBoolean(IS_DARK_THEME, isDarkTheme).apply()

    var isSundayFirst: Boolean
        get() {
            val isSundayFirst = Calendar.getInstance(Locale.getDefault()).firstDayOfWeek == Calendar.SUNDAY
            return mPrefs.getBoolean(SUNDAY_FIRST, isSundayFirst)
        }
        set(sundayFirst) = mPrefs.edit().putBoolean(SUNDAY_FIRST, sundayFirst).apply()

    var displayWeekNumbers: Boolean
        get() = mPrefs.getBoolean(WEEK_NUMBERS, false)
        set(displayWeekNumbers) = mPrefs.edit().putBoolean(WEEK_NUMBERS, displayWeekNumbers).apply()

    var storedView: Int
        get() = mPrefs.getInt(VIEW, MONTHLY_VIEW)
        set(view) = mPrefs.edit().putInt(VIEW, view).apply()

    var defaultReminderType: Int
        get() = mPrefs.getInt(REMINDER_TYPE, REMINDER_AT_START)
        set(type) {
            var newType = type
            if (newType == REMINDER_CUSTOM && defaultReminderMinutes == 0)
                newType = REMINDER_AT_START

            mPrefs.edit().putInt(REMINDER_TYPE, newType).apply()
        }

    var defaultReminderMinutes: Int
        get() = mPrefs.getInt(REMINDER_MINUTES, 10)
        set(mins) {
            if (mins == 0)
                defaultReminderType = REMINDER_AT_START
            mPrefs.edit().putInt(REMINDER_MINUTES, mins).apply()
        }
}
