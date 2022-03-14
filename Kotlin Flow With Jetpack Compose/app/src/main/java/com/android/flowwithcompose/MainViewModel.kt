package com.android.flowwithcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(currentValue)
        while (currentValue > 0) {
            delay(1000)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()

    }

    private fun collectFlow() {
        viewModelScope.launch {
            countDownFlow.collect { time ->
                println("Time is $time")
            }
        }
    }

    // It will always collect the latest value.
    // Incase the collect block takes too much time to complete its execution and it will receive
    // a new new value the rest execution will be canceled
    private fun collectLatestFlow() {
        viewModelScope.launch {
            countDownFlow.collectLatest { time ->
                delay(1500)
                println("Time is $time")
            }
        }
    }


    private fun collectFlowWithFilter() {
        viewModelScope.launch {
            countDownFlow.
            filter { time ->
                time % 2 == 0
            }.
            collect { time ->
                println("Time is $time")
            }
        }
    }
}