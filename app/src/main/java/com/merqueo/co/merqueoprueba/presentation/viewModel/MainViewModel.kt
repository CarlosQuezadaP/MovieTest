package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merqueo.co.usecases.usecases.IDeleteMoviesFromShopUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
class MainViewModel(private val iDeleteMoviesFromShopUseCase: IDeleteMoviesFromShopUseCase) :
    ViewModel() {

    val totalCart = MutableLiveData<Int>()
    var coroutineScope = CoroutineScope(Dispatchers.IO)
    init {
        getCountStore()
    }

    fun getCountStore() {
        coroutineScope.launch {
            val response = iDeleteMoviesFromShopUseCase.countMovies()
            withContext(Dispatchers.Main) {
                response.collect {
                    totalCart.value = it
                }
            }
        }
    }



}