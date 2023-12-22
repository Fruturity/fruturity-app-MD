package com.ardine.fruturity.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.response.FruitResponse
import com.ardine.fruturity.repositories.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: Repository) : ViewModel() {
    private val _resultState = MutableStateFlow<ResultState<List<FruitResponse>>>(ResultState.Loading)
    val resultState: StateFlow<ResultState<List<FruitResponse>>> = _resultState

    fun updateBookmarkStatus(fruitId: String, newStatus: Boolean) {
        viewModelScope.launch {
            _resultState.value = ResultState.Loading
            repository.updateBookmarkStatus(fruitId, newStatus)
        }
    }

    fun getAllBookmarkFruits() {
        viewModelScope.launch {
            try {
                val response = repository.getBookmarkFruits()
                _resultState.value = ResultState.Success(response)
            } catch (e: Exception) {
                _resultState.value = ResultState.Error(e)
            }
        }
    }

}