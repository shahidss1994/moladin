package com.moladin.com.moladin.koin

import android.content.Context
import com.moladin.R
import com.moladin.com.moladin.dataSource.DataSourceManagerImpl
import com.moladin.com.moladin.dataSource.RemoteDataSourceImpl
import com.moladin.com.moladin.dataSource.SharedPrefDataSourceImpl
import com.moladin.com.moladin.retrofit.RetrofitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    single { RetrofitFactory.makeRetrofitService() }

    single {
        androidContext()
            .getSharedPreferences(
                androidContext()
                    .resources.getString(R.string.key_pref), Context.MODE_PRIVATE
            )
    }

    single { RemoteDataSourceImpl(get()) }

    single { SharedPrefDataSourceImpl(get()) }

    single { DataSourceManagerImpl(get(), get()) }

}

