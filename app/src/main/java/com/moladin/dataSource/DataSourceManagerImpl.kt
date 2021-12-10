package com.moladin.com.moladin.dataSource

import java.io.Serializable

class DataSourceManagerImpl(
    private val sharedPrefDataSourceImpl: SharedPrefDataSourceImpl,
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) : DataSourceManager {

    override suspend fun getGetData(
        repositoriesCallBack: RepositoriesCallBack,
        endPoint: String,
        queryMap: Map<String, String>
    ) {
        remoteDataSourceImpl.getGetData(repositoriesCallBack, endPoint, queryMap)
    }

    override suspend fun getPostData(
        repositoriesCallBack: RepositoriesCallBack,
        endPoint: String,
        body: String?,
        queryMap: Map<String, String>
    ) {
        remoteDataSourceImpl.getPostData(repositoriesCallBack, endPoint, body, queryMap)
    }

    override fun <T : Any> putPrefData(data: T, key: String) {
        sharedPrefDataSourceImpl.putPrefData(data, key)
    }

    override fun <T : Any> getPrefData(key: String, defaultValue: T?): Serializable? =
        sharedPrefDataSourceImpl.getPrefData(key, defaultValue)


}