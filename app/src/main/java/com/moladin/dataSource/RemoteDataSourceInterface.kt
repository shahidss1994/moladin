package com.moladin.com.moladin.dataSource

interface RemoteDataSourceInterface {
    suspend fun getGetData(repositoriesCallBack: RepositoriesCallBack, endPoint: String, queryMap: Map<String,String> = HashMap())
    suspend fun getPostData(repositoriesCallBack: RepositoriesCallBack, endPoint: String, body: String ?= null, queryMap: Map<String,String> = HashMap())
}