package com.ardine.fruturity.ui.screen.detail

//import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardine.fruturity.repositories.Repository
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.response.AddNoteResponse
import com.ardine.fruturity.data.response.FruitResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _resultState: MutableStateFlow<ResultState<FruitResponse>> =
        MutableStateFlow(ResultState.Loading)
    val resultState: StateFlow<ResultState<FruitResponse>>
        get() = _resultState

    private val _noteState: MutableStateFlow<ResultState<AddNoteResponse>> =
        MutableStateFlow(ResultState.Loading)
    val noteState: StateFlow<ResultState<AddNoteResponse>>
        get() = _noteState

    fun getFruitById(id: String) {
        viewModelScope.launch {
            try {
                val response = repository.getFruitById(id)
                _resultState.value = ResultState.Success(response)
            } catch (e: Exception) {
                _resultState.value = ResultState.Error("Error ${e.message.toString()}")
            }
        }
    }

    fun addNoteToFruit(id: String, note: String) {
//    : FruitResponse = repository.addNoteToFruit(id,note)
        viewModelScope.launch {
            try {
                val response = repository.addNoteToFruit(id, note)
                _noteState.value = response
                if (response is ResultState.Success) {
                    getFruitById(id)
                } else {
                    _noteState.value = response
                }
            } catch (e: Exception) {
                _noteState.value = ResultState.Error("Error ${e.message.toString()}")
            }
        }
    }
}
