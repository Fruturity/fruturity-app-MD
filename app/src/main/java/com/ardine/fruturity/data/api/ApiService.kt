package com.ardine.fruturity.data.api

import com.ardine.fruturity.data.request.UpdateBookmarkRequest
import com.ardine.fruturity.data.response.BookmarkResponse
import com.ardine.fruturity.data.response.FruitResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //HISTORY
    @GET("/fruit/get")
    suspend fun getFruits(): List<FruitResponse>

    //DETAIL
    @GET("/fruit/{id}")
    suspend fun getFruitById(
        @Path("id") id: String
    ): FruitResponse

    //BOOKMARK
    @GET("/fruit/bookmark")
    suspend fun getBookmarkedFruits(): List<FruitResponse>

    @POST("/fruit/{id}/bookmark")
    suspend fun updateFruitBookmarked(
        @Path("id") id: String,
        @Body request: UpdateBookmarkRequest,
    ): BookmarkResponse

    @DELETE("/fruit/delete/{id}")
    fun deleteFruitById(
        @Path("id") id: String
    ): FruitResponse

}