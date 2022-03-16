# KotlinFlow
## Table of content
* [Guide Line And Video Link](#Guide-Line-And-Video-Link)
* [What is Kotlin Flow](#What-is-Kotlin-Flow)
* [What is Kotlin Flow Operator](#What-is-Kotlin-Flow-Operator)


### Guide Line And Video Link

### What is Kotlin Flow
* A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
* Each emitted value is processed by all the intermediate operators from upstream to downstream and is then delivered to the terminal operator after.
* 
* Flows are cold, so we receive the data once we start to collect it.
* It is a Kotlin language feature that serves as reactive programming framework.
* It's all about being notified about changes in your code and sending them through a pipeline that potentially modifies them.
* Kotlin flow is an addition to Kotlin coroutines. Coroutines save us from callback hell by giving us the possibility to run asynchronous code as if it was synchronous.
  Flow take this a step furthur by adding streams.

### What is Kotlin Flow Operator
* It is just something that decides what happens with an emission of a flow.
* We can apply operators to actually transform our emissions for example is not only for transform emissions but that is one part of flow operators.
* We also have terminate flow operators which are used to terminate the flow.
* Flatten flows, if we have two flows then we can combine these to have the results coming in a single flow.

