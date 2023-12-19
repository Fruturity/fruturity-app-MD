package com.ardine.fruturity.di

import com.ardine.fruturity.data.api.ApiConfig
import com.ardine.fruturity.data.repositories.Repository

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}