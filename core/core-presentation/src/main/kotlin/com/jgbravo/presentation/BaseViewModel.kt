package com.jgbravo.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgbravo.commons.timber.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

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

    protected fun emitLoading() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UiState.Loading)
        }
    }

    protected fun emitError(@StringRes title: Int, @StringRes message: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UiState.Error(title, message))
        }
    }

    sealed class UiState {
        object Loading : UiState()
        class Error(@StringRes val title: Int, @StringRes val message: Int) : UiState()
    }
}