package com.android.flowwithcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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
        flattenFlow()

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
                println("We are in collect latest")
                delay(1500)
                println("Time is $time")
            }
        }
    }


    private fun collectFlowWithFilter() {
        viewModelScope.launch {
            countDownFlow.filter { time ->
                time % 2 == 0
            }.collect { time ->
                println("Time is $time")
            }
        }
    }


    private fun collectFlowWithFilterAndMap() {
        viewModelScope.launch {
            val flow = countDownFlow.filter { time ->
                time % 2 == 0
            }.map { time ->
                time * time
            }

            flow.collect {

            }
        }
    }


    private fun collectFlowWithFilterAndMapAndOnEach() {
        viewModelScope.launch {
            countDownFlow.filter { time ->
                time % 2 == 0
            }.map { time ->
                time * time
            }.onEach { time ->   // OnEach will not end the flow as collect do so
                println("Time is $time")
            }.collect { time ->
                println("Time is $time")
            }
        }
    }


    private fun collectFlowWithOnEach() {
        countDownFlow.onEach { time ->   // OnEach launch similar like collect
            println("Time is $time")
        }.launchIn(viewModelScope)
    }


    private fun collectFlowWithCount() {
        viewModelScope.launch {
            val count = countDownFlow.count { time ->
                time % 2 == 0
            }
            println("Count is $count")
        }
    }


    private fun collectFlowWithReduce() {
        viewModelScope.launch {
            val result = countDownFlow.reduce { accumulator, value ->
                accumulator + value
            }
            println("Result is $result")
        }
    }


    private fun collectFlowWithFold() {
        viewModelScope.launch {
            val result = countDownFlow.fold(100) { accumulator, value ->
                accumulator + value
            }
            println("Result is $result")
        }
    }

    private fun collectFlowWithTake() {
        viewModelScope.launch {
            countDownFlow
                .take(4)
                .collect { time ->
                    println("Time is $time")
                }
        }
    }

    private fun collectFlowWithTakeWhile() {
        viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            (1..1000).asFlow()
                .takeWhile { System.currentTimeMillis() - startTime < 10 }
                .collect { time ->
                    println("Time is $time")
                }
        }
    }

    private fun zipNormalExample(){
        // It is used to combine 2 flows
        val num = (1..3).asFlow()
        val strs = flowOf("one","two","three")
        viewModelScope.launch {
            num.zip(strs){a,b -> "$a -> $b"} // compose a single string with "zip"
                .collect { value ->
                    println("value $value")
                }
        }
    }

    private fun zipWhenOneCompletesBeforeAnother(){
        // It is used to combine 2 flows
        val num = (1..3).asFlow()
        val strs = flowOf("one","two","three","four")
        viewModelScope.launch {
            num.zip(strs){a,b -> "$a -> $b"} // compose a single string with "zip"
                .collect { value ->
                    println("value $value")
                }
        }
    }

    private fun zipWhenOneEmitsAfterSomeDelay(){
        // It is used to combine 2 flows
        val num = (1..3).asFlow().onEach { delay(500) }
        val strs = flowOf("one","two","three").onEach { delay(600) }
        viewModelScope.launch {
            num.zip(strs){a,b -> "$a -> $b"} // compose a single string with "zip"
                .collect { value ->
                    println("value $value")
                }
        }
    }

    private fun combineWhenOneEmitsAfterSomeDelay(){
        // It is used to combine 2 flows
        val num = (1..3).asFlow().onEach { delay(500) }
        val strs = flowOf("one","two","three").onEach { delay(600) }
        viewModelScope.launch {
            num.combine(strs){a,b -> "$a -> $b"} // compose a single string with "zip"
                .collect { value ->
                    println("value $value")
                }
        }
    }


    // Flatten flows, if we have two flows then we can combine these to have the results coming in a single flow.

    private fun flattenFlow() {

            val flow1 = flow<Int> {
                emit(1)
                delay(500)
                emit(2)
            }

        viewModelScope.launch {
            flow1.flatMapConcat { value ->
                flow {
                    emit(value + 1)
                    delay(500)
                    emit(value + 1)
                }
            }.collect { value ->
                println("mainFlow $value")
            }
        }
    }
}