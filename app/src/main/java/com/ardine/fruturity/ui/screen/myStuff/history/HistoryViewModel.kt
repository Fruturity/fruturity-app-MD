package com.ardine.fruturity.ui.screen.myStuff.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardine.fruturity.data.database.Repository
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.response.FruitResponse
import com.ardine.fruturity.ui.screen.myStuff.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: Repository) : ViewModel() {
    private val _resultState: MutableStateFlow<ResultState<List<FruitResponse>>> = MutableStateFlow(ResultState.Loading)
    val resultState: StateFlow<ResultState<List<FruitResponse>>>
        get() = _resultState

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState


//    fun getAddedMarkedFruits() {
//        viewModelScope.launch {
//            repository.getAddedMarkedFruits()
//                .catch { exception ->
//                    _resultState.value = ResultState.Error(exception.message.toString())
//                }
//                .collect { marked ->
//                    _resultState.value = ResultState.Success(marked)
//                }
//        }
//    }

//    fun updateFruitMark(fruitId: Long){
//        viewModelScope.launch {
//            repository.updateFruit(fruitId)
//                .collect { isUpdated ->
//                    if (isUpdated) {
//                        getAddedMarkedFruits()
//                    }
//                }
//        }
//    }

    fun getAllFruits() {
        viewModelScope.launch {
            try {
                val response = repository.getAllFruits()
                _resultState.value = ResultState.Success(response)
            } catch (e: Exception) {
                _resultState.value = ResultState.Error(e.message.toString())
            }
        }
    }

//    fun onQueryChange(query: String){
//        _searchState.value = _searchState.value.copy(query = query)
//        searchFruits(query)
//    }
//
//    private fun searchFruits(query: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.searchFruits(query)
//                .catch { _resultState.value = ResultState.Error(it.message.toString()) }
//                .collect { _resultState.value = ResultState.Success(it) }
//        }
//    }
}