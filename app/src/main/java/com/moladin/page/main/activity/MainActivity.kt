package com.moladin.com.moladin.page.main.activity

import android.os.Bundle
import com.moladin.com.moladin.commom.ui.BaseActivity
import com.moladin.com.moladin.page.main.adapter.RepoListAdapter
import com.moladin.com.moladin.page.main.viewModel.MainViewModel
import com.moladin.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class.java) {

    private val mViewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun getViewModel() = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            viewModel = mViewModel
            viewState = mViewModel.mainViewState
            with(recyclerView) {
                adapter = RepoListAdapter(mViewModel)
            }
        }
        setContentView(binding.root)
        mViewModel.getData()
    }

}