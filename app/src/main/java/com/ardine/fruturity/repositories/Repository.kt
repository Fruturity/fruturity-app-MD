package com.ardine.fruturity.repositories

import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.api.ApiService
import com.ardine.fruturity.data.request.AddNoteRequest
import com.ardine.fruturity.data.request.UpdateBookmarkRequest
import com.ardine.fruturity.data.response.AddNoteResponse
import com.ardine.fruturity.data.response.FruitResponse

class Repository private constructor(
    private val apiService: ApiService,
){
    private  val fruits = mutableListOf<FruitResponse>()

    suspend fun getBookmarkFruits(): List<FruitResponse> {
        try {
            val response = apiService.getBookmarkedFruits()
            fruits.addAll(response)
            return response
        } catch (e: Exception){
            e.printStackTrace()
            throw e
        }
    }

    suspend fun getAllFruits() : List<FruitResponse>{
        return apiService.getFruits()
    }

    suspend fun getFruitById(id: String): FruitResponse {
        return apiService.getFruitById(id)
    }

    fun addNoteToFruit(id: String, note: String): ResultState<AddNoteResponse> {
        return try {
            val response = apiService.addNoteToFruit(id, AddNoteRequest(note))
            ResultState.Success(response)
        } catch (e: Exception) {
            ResultState.Error(e)
        }
    }

//
//    fun addNoteToFruit(id: String, note: String): FruitResponse {
//        val request = AddNoteRequest(note)
//        return apiService.addNoteToFruit(id, request)
//    }


//    suspend fun deleteFruitById(id: String): Result<String> {
//        return try {
//            val response = apiService.deleteFruitById(id)
//            Result.Success(response.message)
//        } catch (e: Exception) {
//            Result.Error(e)
//        }
//    }

    suspend fun updateBookmarkStatus(id: String, newStatus: Boolean): ResultState<String> {
        return try {
            val request = UpdateBookmarkRequest(newStatus)
            val response = apiService.updateFruitBookmarked(id, request)
            ResultState.Success(response.message)
        } catch (e: Exception) {
            ResultState.Error(e)
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            apiService: ApiService,
        ): Repository =
            instance ?: synchronized(this) {
                Repository(apiService).apply {
                    instance = this
                }
            }
    }
}