package com.moladin.com.moladin.dataSource

import android.content.SharedPreferences
import java.io.Serializable

class SharedPrefDataSourceImpl(private val sharedPreference: SharedPreferences): SharedPrefDataSourceInterface {
    override fun <T : Any> putPrefData(data: T, key: String) {
        when(data) {
            is Int-> sharedPreference.edit().putInt(key, data).apply()
            is String -> sharedPreference.edit().putString(key, data).apply()
            is Boolean -> sharedPreference.edit().putBoolean(key, data).apply()
        }
    }

    override fun <T : Any> getPrefData(key: String, defaultValue: T?): Serializable? {
        return when(defaultValue) {
            is Int-> sharedPreference.getInt(key, defaultValue)
            is String -> sharedPreference.getString(key, defaultValue)
            is Boolean -> sharedPreference.getBoolean(key, defaultValue)
            else -> IllegalArgumentException("Type mismatch")
        }
    }
}