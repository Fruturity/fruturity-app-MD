package com.ardine.fruturity.data.repositories

import androidx.lifecycle.liveData
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.api.ApiService
import com.ardine.fruturity.data.api2.ApiService2
import com.ardine.fruturity.data.request.AddNoteRequest
import com.ardine.fruturity.data.response.FruitResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class Repository private constructor(
    private val apiService: ApiService,
    private val apiService2: ApiService2
){
    private  val historyFruits = mutableListOf<FruitResponse>()
    private  val bookmarkFruits = mutableListOf<FruitResponse>()

    suspend fun getBookmarkFruits(): List<FruitResponse> {
        try {
            val response = apiService.getBookmarkedFruits()
            bookmarkFruits.addAll(response)
            return response
        } catch (e: Exception){
            e.printStackTrace()
            throw e
        }
    }

    suspend fun getAllFruits(): List<FruitResponse> {
        try {
            val response = apiService.getFruits()
            historyFruits.addAll(response)
            return response
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun getFruitById(id: String): FruitResponse {
        return apiService.getFruitById(id)
    }

    suspend fun addNoteToFruit(id: String, note: String): ResultState<String> {
        return try {
            val request = AddNoteRequest(note)
            val response = apiService.addNoteToFruit(id, request)
            ResultState.Success(response.message)
        } catch (e: Exception) {
            ResultState.Error("Error ${e.message.toString()}")
        }
    }

    //upload image
//    suspend fun uploadImagePrediction(
//        imageFile : MultipartBody.Part
//    ) : ResultState<UploadImagePredectionResponse>{
//        return withContext(Dispatchers.IO){
//            try {
//                val response = apiService.uploadImagePredict(imageFile)
//                ResultState.Success(response)
//            }catch (e : Exception){
//                ResultState.Error(e)
//            }
//        }
//    }

    fun uploadImagePredection(
        imageFile : MultipartBody.Part
    ) = liveData {
//        val requestFile = imageFile.asRequestBody("file/jpg".toMediaType())
//        val file = MultipartBody.Part.createFormData(
//            "file",image.name,requestFile
//        )
        emit(ResultState.Loading)
        try {
            val response = apiService2.uploadImagePredict(imageFile)
            emit(ResultState.Success(response))
        }catch (e : Exception){
            emit(ResultState.Error("Error ${e.message.toString()}"))
        }
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
            // Log.e("Camera view Model", "Error", e)
            emit(ResultState.Error("Error ${e.message.toString()}"))
        }
    }




//    suspend fun deleteFruitById(id: String): Result<String> {
//        return try {
//            val response = apiService.deleteFruitById(id)
//            Result.Success(response.message)
//        } catch (e: Exception) {
//            Result.Error(e)
//        }
//    }


//    suspend fun getAllMarkedFruits(): List<FruitResponse> {
//        try {
//            val response = apiService.getFruits()
//            fruits.addAll(response)
//            return response
//        } catch (e: Exception) {
//            // Handle the exception (e.g., logging, error reporting)
//            e.printStackTrace()
//            throw e
//        }
//    }

//    fun updateBookmarkStatus(fruitId: Long) {
//        val index = fruits.indexOfFirst { it.fruits.id == fruitId }
//        if (index >= 0) {
//            val fruitMarked = fruits[index]
//            fruits[index] = fruitMarked.copy(fruits = fruitMarked.fruits.copy(isBookmark = !fruitMarked.fruits.isBookmark))
//        }
//    }

//    fun searchFruits(query: String){
//        return getAllFruits()
//            .map { result ->
//                result.filter {
//                    it.fruits.category.contains(query, ignoreCase = true)
//                }
//            }
//        try {
//            val apiFruits = apiService.getFruits()
//            val fruitHistoryList = apiFruits.map { FruitHistory(it, false) }
//            fruits.addAll(fruitHistoryList)
//        } catch (e: Exception) {
//            // Handle the exception (e.g., logging, error reporting)
//            e.printStackTrace()
//        }
//    }

//    BOOKMARK
//    fun updateFruit(fruitId: Long): Flow<Boolean> {
//        val index = fruits.indexOfFirst { it.fruits.id == fruitId }
//        val result = if (index >= 0) {
//            val fruitMarked = fruits[index]
//            fruits[index] = fruitMarked.copy(fruits = fruitMarked.fruits)
//            true
//        } else {
//            false
//        }
//        return flowOf(result)
//    }
//
//    fun getAddedMarkedFruits(): Flow<List<FruitHistory>>{
//        return getAllFruits()
//            .map{ marked ->
//                marked.filter { mark ->
//                    mark.isBookmark == true
//                }
//            }
//    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            apiService: ApiService,
            apiService2: ApiService2
        ): Repository =
            instance ?: synchronized(this) {
                Repository(apiService,apiService2).apply {
                    instance = this
                }
            }
    }
}