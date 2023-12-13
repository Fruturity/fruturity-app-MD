package com.ardine.fruturity.ui.screen.myStuff.bookmark

import androidx.lifecycle.ViewModel
import com.ardine.fruturity.data.Repository
import com.ardine.fruturity.data.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BookmarkViewModel(private val repository: Repository) : ViewModel() {
    private val _resultState: MutableStateFlow<ResultState<BookmarkState>> = MutableStateFlow(ResultState.Loading)
    val resultState: StateFlow<ResultState<BookmarkState>>
    get() = _resultState


}