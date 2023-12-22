package com.ardine.fruturity.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardine.fruturity.repositories.Repository
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.response.FruitResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _resultState: MutableStateFlow<ResultState<FruitResponse>> =
        MutableStateFlow(ResultState.Loading)
    val resultState: StateFlow<ResultState<FruitResponse>>
        get() = _resultState

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

    fun deleteById(id:String) = repository.deleteFruitById(id)
}
