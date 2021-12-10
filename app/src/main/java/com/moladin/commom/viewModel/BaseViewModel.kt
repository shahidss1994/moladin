package com.moladin.com.moladin.commom.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moladin.com.moladin.dataSource.DataSourceManagerImpl
import org.koin.core.component.KoinComponent

open class BaseViewModel(dataSource: DataSourceManagerImpl) : ViewModel(), KoinComponent {

    val mToastLiveData = MutableLiveData<String>()

}