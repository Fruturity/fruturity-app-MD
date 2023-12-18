package com.ardine.fruturity.data.api

import com.ardine.fruturity.data.response.FruitResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/fruit/get")
    suspend fun getFruits(): List<FruitResponse>

    @GET("/fruit/{id}")
    suspend fun getFruitById(
        @Path("id") id: String
    ): FruitResponse

}