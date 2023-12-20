package com.ardine.fruturity.data.api

import com.ardine.fruturity.data.request.AddNoteRequest
import com.ardine.fruturity.data.response.AddNoteResponse
import com.ardine.fruturity.data.response.BookmarkResponse
import com.ardine.fruturity.data.response.FruitResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    @POST("/fruit/{id}/add/note")
    suspend fun addNoteToFruit(
        @Path("id") id: String,
        @Body request: AddNoteRequest
    ): AddNoteResponse

    @DELETE("/fruit/delete/{id}")
    suspend fun deleteFruitById(@Path("id") id: String): AddNoteResponse
}