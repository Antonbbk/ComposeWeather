package com.example.compose.state

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class StateViewModel<T, E : AppError> : ViewModel() {
    val state = mutableStateOf(ScreenState<T, E>(isLoading = true))

//    protected val _screenAction = MutableSharedFlow<ScreenAction>()
//    val screenAction: SharedFlow<ScreenAction> = _screenAction.asSharedFlow()

    fun onErrorHandled() {
        state.errorHandled()
    }

    fun load(
        block: suspend () -> E?,
    ) {
        state.loading()
        viewModelScope.launch {
            val result = block()
            state.loaded()
            state.value = state.value.copy()
        }
    }

//    protected fun emitScreenAction(screenAction: ScreenAction) {
//        viewModelScope.launch {
//            _screenAction.emit(screenAction)
//        }
//    }
}