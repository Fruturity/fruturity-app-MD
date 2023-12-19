package com.ardine.fruturity.data.api

import com.ardine.fruturity.data.response.BookmarkResponse
import com.ardine.fruturity.data.response.FruitResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/fruit/get")
    suspend fun getFruits(): List<FruitResponse>

    @GET("/fruit/{id}")
    suspend fun getFruitById(
        @Path("id") id: String
    ): FruitResponse

    @GET("/fruit/bookmark")
    suspend fun getBookmarkedFruits(): List<FruitResponse>

    @POST("/fruit/{id}/bookmark")
    suspend fun bookmarkFruit(
        @Path("id") fruitId: String
        ): BookmarkResponse
}