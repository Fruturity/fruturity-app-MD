package com.ardine.fruturity.di

import com.ardine.fruturity.data.api.ApiConfig
import com.ardine.fruturity.data.api2.ApiConfig2
import com.ardine.fruturity.repositories.Repository

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        val apiService2 = ApiConfig2.getApiService()
        return Repository.getInstance(apiService,apiService2)
    }
}