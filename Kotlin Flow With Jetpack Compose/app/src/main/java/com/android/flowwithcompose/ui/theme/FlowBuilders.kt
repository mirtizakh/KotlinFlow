package com.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

private fun setupFixedFlow() {
    val fixedFlow = flowOf(1, 2, 3, 4, 5).onEach { delay(500) }
}

private fun setupFlowFromCollection() {
    val collectionFlow = listOf(1, 2, 3, 4, 5).asFlow().onEach { delay(500) }
}

private fun setupFlowWithLambda() {
    val lambdaFlow = flow {
        (1..5).forEach { value ->
            delay(500)
            emit(value)
        }
    }
}

private fun setupChannelFlowWithLambda() {
    val channelFlow = channelFlow {
        (1..5).forEach { value ->
            delay(500)
            send(value)
        }
    }
}

