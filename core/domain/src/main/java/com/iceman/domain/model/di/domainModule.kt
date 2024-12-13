package com.iceman.domain.model.di

import com.iceman.domain.model.usecase.ConfirmRideUseCase
import com.iceman.domain.model.usecase.EstimateTripValueUseCase
import com.iceman.domain.model.usecase.GetRideUseCase
import org.koin.dsl.module

val domainModule = module {
    single{ GetRideUseCase(get()) }
    single{ EstimateTripValueUseCase(get()) }
    single{ ConfirmRideUseCase(get()) }
}