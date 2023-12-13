package com.ardine.fruturity.ui.screen.myStuff.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardine.fruturity.data.Repository
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.model.FruitHistory
import com.ardine.fruturity.model.Fruits
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _resultState: MutableStateFlow<ResultState<FruitHistory>> =
        MutableStateFlow(ResultState.Loading)
    val resultState: StateFlow<ResultState<FruitHistory>>
        get() = _resultState

    fun getFruitById(fruitId: Long) {
        viewModelScope.launch {
            _resultState.value = ResultState.Loading
            _resultState.value = ResultState.Success(repository.getFruitById(fruitId))
        }
    }

    fun addBookmark (fruit: Fruits, count: Int) {
        viewModelScope.launch {
            repository.updateFruit(fruit.id, count)
        }
    }
}