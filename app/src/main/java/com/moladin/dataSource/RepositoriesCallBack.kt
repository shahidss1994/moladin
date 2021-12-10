package com.moladin.com.moladin.dataSource

interface RepositoriesCallBack {
    fun onResponse(response: String)
    fun onFailure(response: String)
    fun noInternet(e: Exception)
    fun serverError(e: Exception)
}