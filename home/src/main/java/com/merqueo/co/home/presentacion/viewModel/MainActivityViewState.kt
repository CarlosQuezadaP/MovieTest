package com.merqueo.co.home.presentacion.viewModel

sealed class MainActivityViewState {
    object ShowLoading : MainActivityViewState()
    class ShowError(val error: Throwable) : MainActivityViewState()
}