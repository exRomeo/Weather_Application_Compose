package com.trianglz.weatherapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModelMVI<UIState, UIEvent, UIAction> : ViewModel() {

    protected abstract val initialUIState: UIState
    protected abstract fun processAction(action: UIAction)

    protected var lastUIState: UIState = initialUIState

    private val _uiState = MutableStateFlow(initialUIState)
    val uiState:StateFlow<UIState>
        get() = _uiState

    private val _uiEvents = MutableSharedFlow<UIEvent>()
    val uiEvents: SharedFlow<UIEvent>
        get() = _uiEvents

    private val _uiActions = MutableSharedFlow<UIAction>()

    init {
        viewModelScope.launch {
            _uiActions.collect { action ->
                processAction(action)
            }
        }
    }

    protected open fun updateUIState(state: UIState) {
        _uiState.value = state
        lastUIState = state
    }

    protected fun sendEvent(event: UIEvent) = viewModelScope.launch {
        _uiEvents.emit(event)
    }

    fun applyAction(action: UIAction) = viewModelScope.launch {
        _uiActions.emit(action)
    }
}