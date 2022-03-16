package com.project.daggermultibindingmvvmsample.activity.main

import com.project.daggermultibindingmvvmsample.SingleDataModel

sealed class MainActivityViewState {
    object ShowLoading : MainActivityViewState()
    class ShowError(val error: Throwable) : MainActivityViewState()
    class ShowData(val data: List<SingleDataModel>) : MainActivityViewState()
}