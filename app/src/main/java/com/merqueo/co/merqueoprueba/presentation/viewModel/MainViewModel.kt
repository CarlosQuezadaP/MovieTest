package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merqueo.co.usecases.usecases.IDeleteMoviesFromShopUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf

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
                flowOf(response)
                    .collect {
                        it.collect {
                            totalCart.value = it
                        }
                    }
            }
        }
    }

    fun deleteAll() {
        coroutineScope.launch {
            iDeleteMoviesFromShopUseCase.invoke()
        }
    }

}