package com.example.draganddroplazycolumn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScreenViewModel : ViewModel() {
    private val _list = MutableStateFlow(Music.samples())
    val list: StateFlow<List<Music>> = _list

    fun updateList(list: List<Music>) {
        viewModelScope.launch {
            _list.emit(list)
        }
    }
}