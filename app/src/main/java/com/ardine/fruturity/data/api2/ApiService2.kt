package com.ardine.fruturity.data.api2

import com.ardine.fruturity.data.response.UploadImagePredectionResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService2 {

    @Multipart
    @POST("/prediction")
    suspend fun uploadImagePredict(
        @Part image: MultipartBody.Part
    ): UploadImagePredectionResponse


}