package com.moladin.com.moladin.commom.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.moladin.BR
import com.moladin.com.moladin.commom.bind

class ItemRepoViewState(initId: Int? = -1, initName: String? = "") : BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var name by bind(BR.name, initName)
}