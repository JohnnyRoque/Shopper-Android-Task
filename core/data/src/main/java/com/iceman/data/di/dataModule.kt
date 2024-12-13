package com.iceman.data.di

import com.iceman.data.network.interceptor.ApiInterceptor
import com.iceman.data.network.service.NetworkDataSource
import com.iceman.data.network.service.ShopperNetworkApiImpl
import com.iceman.data.repository.RideRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module

val dataModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder().addInterceptor(ApiInterceptor()).build()
    }

    single<NetworkDataSource> { ShopperNetworkApiImpl(get()) }

    single<RideRepository> { RideRepository(get()) }

}