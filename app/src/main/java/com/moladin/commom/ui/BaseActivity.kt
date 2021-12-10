package com.moladin.com.moladin.commom.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.moladin.com.moladin.commom.viewModel.BaseViewModel
import com.moladin.com.moladin.dataSource.DataSourceManagerImpl
import org.koin.android.ext.android.inject

abstract class BaseActivity<VM : BaseViewModel>(private val mViewModelClass: Class<VM>) :
    AppCompatActivity() {

    val dataSource by inject<DataSourceManagerImpl>()

    lateinit var toast: Toast

    abstract fun getViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast = Toast(this@BaseActivity)
        getViewModel().mToastLiveData.observe(this@BaseActivity, {
            toast.cancel()
            if (it.isNotEmpty()) {
                toast.setText(it)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()
            }
        })
    }

}