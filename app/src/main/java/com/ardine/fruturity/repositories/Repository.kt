package com.ardine.fruturity.repositories

import androidx.lifecycle.liveData
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.api.ApiService
import com.ardine.fruturity.data.api2.ApiService2
import com.ardine.fruturity.data.request.UpdateBookmarkRequest
import com.ardine.fruturity.data.response.FruitResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class Repository private constructor(
    private val apiService: ApiService,
    private val apiService2: ApiService2,
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

    fun uploadImage(image: File) = liveData {
        emit(ResultState.Loading)
        val requestFile = image.asRequestBody("image/jpg".toMediaType())
        val file = MultipartBody.Part.createFormData(
            "image", image.name, requestFile
        )
        try {
            val successResponse = apiService2.uploadImagePredict(file)
            emit(ResultState.Success(successResponse))
        } catch (e: Exception) {
            emit(ResultState.Error("Error ${e.message.toString()}"))
        }
    }

    fun deleteFruitById(id: String) {
        apiService.deleteFruitById(id)
    }

    suspend fun updateBookmarkStatus(id: String, newStatus: Boolean): ResultState<String> {
        return try {
            val request = UpdateBookmarkRequest(newStatus)
            val response = apiService.updateFruitBookmarked(id, request)
            ResultState.Success(response.message)
        } catch (e: Exception) {
            ResultState.Error("Error ${e.message.toString()}")
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            apiService: ApiService,
            apiService2: ApiService2,
        ): Repository =
            instance ?: synchronized(this) {
                Repository(apiService,apiService2).apply {
                    instance = this
                }
            }
    }
}