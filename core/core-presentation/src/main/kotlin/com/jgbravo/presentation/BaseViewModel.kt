package com.jgbravo.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel : ViewModel() {

    // ToDo: Do not inject logger inside base
    /*@Inject
    protected lateinit var logger: Logger*/

    private val _uiState = MutableSharedFlow<UiState>()
    val uiState: SharedFlow<UiState> get() = _uiState


    /*fun trackLifecycle(activityName: String, function: String) {
        logger.activity(activityName, function)
    }

    fun debug(activityName: String, message: String) {
        logger.d(activityName, message)
    }*/

    protected suspend fun emitLoading() {
        _uiState.emit(UiState.Loading)
    }

    protected suspend fun emitError(@StringRes title: Int, @StringRes message: Int) {
        _uiState.emit(UiState.Error(title, message))
    }

    sealed class UiState {
        object Loading : UiState()
        class Error(@StringRes val title: Int, @StringRes val message: Int) : UiState()
    }
}