package com.iceman.home.di

import com.iceman.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeScreenModule = module{
    viewModel{ HomeViewModel(get(),get()) }
}