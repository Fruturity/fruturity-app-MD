package com.ardine.fruturity.ui.screen.prediction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.repositories.Repository
import com.ardine.fruturity.data.response.UploadImagePredectionResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.http.Multipart

class CameraViewModel(private val repository: Repository) : ViewModel() {



    private val _uploadImage = MediatorLiveData<ResultState<UploadImagePredectionResponse>>()
    val uploadImage : LiveData<ResultState<UploadImagePredectionResponse>> = _uploadImage

    fun uploadImagePredict(imageFile : MultipartBody.Part){
        val liveData = repository.uploadImagePredection(imageFile)
        _uploadImage.addSource(liveData){result ->
            _uploadImage.value = result
        }
    }

    //    private val _uploadImage : MutableStateFlow<ResultState<UploadImagePredectionResponse>> =
//        MutableStateFlow(ResultState.Loading)
//
//    val uploadImage : StateFlow<ResultState<UploadImagePredectionResponse>>
//        get() = _uploadImage

//    fun uploadImage(imageFile : MultipartBody.Part){
//        viewModelScope.launch {
//
//        }
//    }
}