package com.iceman.routedetails.di

import com.iceman.routedetails.ui.RouteDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val routeDetailScreenModule = module {
    viewModel { RouteDetailViewModel(get()) }
}