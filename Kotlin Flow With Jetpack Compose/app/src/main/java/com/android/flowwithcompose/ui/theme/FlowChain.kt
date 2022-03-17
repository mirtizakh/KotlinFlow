package com.flow

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

private suspend fun createFlowChain() {
    flow {
        (1..10).forEach { value ->
            delay(500)
            emit(value)
        }
    }.collect {item->
        Log.d("FlowChain", "collect $item")
    }
}

private suspend fun createFlowChainWithFlowOn() {
    // To deal with flow in two different dispatchers we need to use flow on
    flow {
        (1..10).forEach { value ->
            Log.d("FlowChain", "in flow ${Thread.currentThread().name}")
            delay(500)
            emit(value)
        }
    }.flowOn(Dispatchers.IO)
        .collect { value ->
        Log.d("FlowChain", "in collect ${Thread.currentThread().name}")
        Log.d("FlowChain", "collect $value")
    }
}
