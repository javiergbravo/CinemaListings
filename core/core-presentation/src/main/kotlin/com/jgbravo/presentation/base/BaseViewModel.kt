package com.jgbravo.presentation.base

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.jgbravo.commons.timber.Logger
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var logger: Logger

    private val _uiState = MutableSharedFlow<UiState>()
    val uiState: SharedFlow<UiState> get() = _uiState

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