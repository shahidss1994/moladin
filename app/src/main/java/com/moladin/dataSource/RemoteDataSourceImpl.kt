package com.moladin.com.moladin.dataSource

import com.moladin.com.moladin.retrofit.RetrofitService
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import kotlin.reflect.KSuspendFunction1

class RemoteDataSourceImpl(private val service: RetrofitService) : RemoteDataSourceInterface {

    private val headerMap = HashMap<String, String>()

    override suspend fun getGetData(
        repositoriesCallBack: RepositoriesCallBack,
        endPoint: String,
        queryMap: Map<String, String>
    ) {
        val requestSupport =
            RequestSupport(endPoint, null, queryMap, "get", repositoriesCallBack)
        executeRequest(repositoriesCallBack, requestSupport, ::getBodyStringWithQueryMap)
    }

    override suspend fun getPostData(
        repositoriesCallBack: RepositoriesCallBack,
        endPoint: String,
        body: String?,
        queryMap: Map<String, String>
    ) {
        val requestSupport =
            RequestSupport(endPoint, body, queryMap, "post", repositoriesCallBack)
        executeRequest(repositoriesCallBack, requestSupport, ::getBodyStringWithQueryMap)
    }

    private suspend fun getBodyStringWithQueryMap(requestSupport: RequestSupport) =
        if (requestSupport.requestType.equals("post", true)) {
            service.fetchData(
                requestSupport.endPoint,
                requestSupport.body,
                requestSupport.queryMap,
                headerMap
            )
        } else {
            service.fetchData(
                requestSupport.endPoint,
                requestSupport.queryMap,
                headerMap
            )
        }

    private suspend fun executeRequest(
        repositoriesCallBack: RepositoriesCallBack,
        requestSupport: RequestSupport,
        apiFunctionRef: KSuspendFunction1<RequestSupport, Response<String>>
    ) {
        try {
            val response = apiFunctionRef(requestSupport)
            handleResponse(response, repositoriesCallBack)
        } catch (e: Exception) {
            handleException(e, repositoriesCallBack)
        }
    }

    private fun handleException(
        e: Exception,
        repositoriesCallBack: RepositoriesCallBack
    ) {
        when (e) {
            is ConnectException,
            is UnknownHostException -> repositoriesCallBack.noInternet(e)
            else -> repositoriesCallBack.serverError(e)
        }
    }

    private fun handleResponse(
        response: Response<String>,
        repositoriesCallBack: RepositoriesCallBack
    ) {
        if (response.isSuccessful) {
            response.body()?.let {
                repositoriesCallBack.onResponse(it)
            } ?: run {
                repositoriesCallBack.onFailure(
                    (response.errorBody()?.string() ?: "")
                )
            }
        } else {
            repositoriesCallBack.onFailure(
                (response.errorBody()?.string() ?: "")
            )
        }
    }

    data class RequestSupport(
        val endPoint: String,
        val body: String?,
        val queryMap: Map<String, String>,
        val requestType: String,
        val repositoriesCallBack: RepositoriesCallBack
    )

}