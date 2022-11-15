package com.example.compose.state

import androidx.compose.runtime.MutableState

data class ScreenState<T, E : AppError>(
    val data: T? = null,
    val isLoading: Boolean = true,
    val error: E? = null,
)

fun <T, E : AppError> MutableState<ScreenState<T, E>>.loaded(error: E? = null) {
    this.value = this.value.copy(error = error, isLoading = false)
}

fun <T, E : AppError> MutableState<ScreenState<T, E>>.setData(data: T?) {
    this.value = this.value.copy(data = data)
}

inline fun <T, E : AppError> MutableState<ScreenState<T, E>>.updateData(
    block: (T) -> T,
) {
    val stateData = this.value.data ?: return
    setData(block(stateData))
}

fun <T, E : AppError> MutableState<ScreenState<T, E>>.loading() {
    this.value = this.value.copy(isLoading = true)
}

fun <T, E : AppError> MutableState<ScreenState<T, E>>.load(
    block: () -> E?,
) {
    this.loading()
    this.loaded(
        block()
    )
}

fun <T, E : AppError> MutableState<ScreenState<T, E>>.errorHandled() {
    this.value = this.value.copy(error = null)
}
