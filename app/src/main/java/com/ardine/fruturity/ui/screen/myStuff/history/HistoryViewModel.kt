package com.ardine.fruturity.ui.screen.myStuff.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardine.fruturity.data.Repository
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.model.FruitHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: Repository) : ViewModel() {
    private val _resultState: MutableStateFlow<ResultState<List<FruitHistory>>> = MutableStateFlow(ResultState.Loading)
    val resultState: StateFlow<ResultState<List<FruitHistory>>>
        get() = _resultState

    private val _historyState = mutableStateOf(HistoryState())
    val historyState: State<HistoryState> = _historyState

    fun getAllFruits() {
        viewModelScope.launch {
            repository.getAllFruits()
                .catch {
                    _resultState.value = ResultState.Error(it.message.toString())
                }
                .collect { order ->
                    _resultState.value = ResultState.Success(order)
                }
        }
    }

    fun onQueryChange(query: String){
        _historyState.value = _historyState.value.copy(query = query)
        searchFruits(query)
    }

    private fun searchFruits(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchFruits(query)
                .catch { _resultState.value = ResultState.Error(it.message.toString()) }
                .collect { _resultState.value = ResultState.Success(it) }
        }
    }
}