package com.moladin.com.moladin.page.main.viewModel

import androidx.lifecycle.viewModelScope
import com.moladin.com.moladin.base.Constants
import com.moladin.com.moladin.commom.DataParser
import com.moladin.com.moladin.commom.viewModel.BaseViewModel
import com.moladin.com.moladin.commom.viewState.ItemRepoViewState
import com.moladin.com.moladin.commom.viewState.MainViewState
import com.moladin.com.moladin.dataSource.DataSourceManagerImpl
import com.moladin.com.moladin.dataSource.RepositoriesCallBack
import com.moladin.commom.model.RepositoriesResponseItem
import kotlinx.coroutines.launch

class MainViewModel(val dataSource: DataSourceManagerImpl) : BaseViewModel(dataSource) {

    val mainViewState = MainViewState()

    fun getData() {

        viewModelScope.launch {
            val queryMap = hashMapOf<String, String>()
            queryMap[Constants.Key.SINCE] = Constants.Value.WEEKLY
            dataSource.getGetData(object : RepositoriesCallBack {
                override fun onResponse(response: String) {
                    val arrayList =
                        DataParser.fromJson<ArrayList<RepositoriesResponseItem>>(response)
                    val itemViewStateList = arrayListOf<ItemRepoViewState>()
                    for (i in 0 until (arrayList?.size ?: 0)) {
                        val itemViewState =
                            ItemRepoViewState(i, arrayList?.get(i)?.username ?: "empty")
                        itemViewStateList.add(itemViewState)
                    }
                    mainViewState.list = itemViewStateList
                }

                override fun onFailure(response: String) {
                    //mainViewState.name = response
                }

                override fun noInternet(e: Exception) {
                    //mainViewState.name = e.message
                }

                override fun serverError(e: Exception) {
                    //mainViewState.name = e.message
                }

            }, Constants.ApiUrls.REPO_URL, queryMap)
        }

    }

    fun onClick(text: String) {
        mToastLiveData.value = text
    }

}