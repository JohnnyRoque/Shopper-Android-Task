package com.iceman.shopperdrive.application

import com.iceman.shopperdrive.ui.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AppViewModel() }
}