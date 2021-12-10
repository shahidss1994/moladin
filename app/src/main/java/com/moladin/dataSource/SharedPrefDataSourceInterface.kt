package com.moladin.com.moladin.dataSource

import java.io.Serializable

interface SharedPrefDataSourceInterface {
    fun <T : Any> putPrefData(data: T, key: String)
    fun <T : Any> getPrefData(key: String, defaultValue: T?): Serializable?
}