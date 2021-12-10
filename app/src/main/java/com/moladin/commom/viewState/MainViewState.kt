package com.moladin.com.moladin.commom.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.moladin.BR
import com.moladin.com.moladin.commom.bind

class MainViewState(initList: ArrayList<ItemRepoViewState> = arrayListOf()) : BaseObservable() {

    @get:Bindable
    var list by bind(BR.list, initList)

}