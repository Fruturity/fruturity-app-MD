package com.ardine.fruturity.ui.screen.prediction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.repositories.Repository
import com.ardine.fruturity.data.response.UploadImagePredectionResponse
import com.ardine.fruturity.repositories.Repository
import kotlinx.coroutines.launch
import java.io.File

class CameraViewModel(private val repository: Repository) : ViewModel() {



//    private val _uploadImage = MediatorLiveData<ResultState<UploadImagePredectionResponse>>()
//    val uploadImage : LiveData<ResultState<UploadImagePredectionResponse>>  =_uploadImage

//    private val _uploadImage : MutableStateFlow<ResultState<UploadImagePredectionResponse>> = MutableStateFlow(ResultState.Loading)
//
//    val uploadImage : StateFlow<ResultState<UploadImagePredectionResponse>> get() = _uploadImage
//    fun uploadImagePredict(imageFile : MultipartBody.Part){
//        val liveData = repository.uploadImagePredection(imageFile)
//        _uploadImage.addSource(liveData){result ->
//            _uploadImage.value = result
//        }
//    }
private val _uploadImage = MutableLiveData<ResultState<UploadImagePredectionResponse>>()
    val uploadImage: LiveData<ResultState<UploadImagePredectionResponse>> = _uploadImage
    fun uploadImagePredict(file: File) {
        viewModelScope.launch {
            repository.uploadImage(file).asFlow().collect {
                _uploadImage.value = it
            }
        }
    }

//    fun observeUploadImage(owner: LifecycleOwner, observer: Observer<ResultState<UploadImagePredectionResponse>>) {
//        _uploadImage.observe(owner, observer)
//    }

//    fun uploaImagePredict(imageFile : MultipartBody.Part){
//        viewModelScope.launch {
//            val response = repository.uploadImagePredection(imageFile)
//            _uploadImage.value = ResultState.Success(response)
//        }
//    }

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