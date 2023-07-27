package com.meet.project.easylogin.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class Prefs {

    companion object {
        private lateinit var prefs: SharedPreferences

        const val LOGGED_IN = "loggedIn"

        fun contains(key: String) = prefs.contains(key)

        fun initPrefs(context: Context) {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun putBoolean(key: String, value: Boolean) {
            with(prefs.edit()) {
                putBoolean(key, value)
                apply()
            }
        }

        fun getBoolean(key: String, defaultValue: Boolean? = null): Boolean {
            return prefs.getBoolean(key, defaultValue ?: false)
        }

        fun clearAllData() {
            with(prefs.edit()) {
                clear()
                apply()
            }
        }
    }
}