package com.iceman.ui.di

import com.iceman.ui.places.AutocompleteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module{

    viewModel{ AutocompleteViewModel(get()) }
}