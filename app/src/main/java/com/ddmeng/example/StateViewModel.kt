package com.ddmeng.example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StateViewModel<S : Any> : ViewModel() {
    abstract fun configureInitialState(): S
    private val _flow by lazy {
        MutableStateFlow(configureInitialState())
    }
    private val stateFlow = _flow.asStateFlow()

    var state: S
        get() = stateFlow.value
        protected set(value) {
            _flow.value = value
        }

    @Composable
    fun collectAsState(): State<S> {
        return stateFlow.collectAsState()
    }
}

