package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.usecases.usecases.IDeleteMoviesFromShopUseCase
import com.merqueo.co.usecases.usecases.IGetMoviesShopCarUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelShopping(
    private val iGetMoviesShopCarUseCase: IGetMoviesShopCarUseCase,
    private val iDeleteMoviesFromShopUseCase: IDeleteMoviesFromShopUseCase
) :
    ViewModel() {

    var coroutineScope = CoroutineScope(Dispatchers.IO)
    var movieList = MutableLiveData<List<MovieItemDomain>>()

    init {
        getFromLocal()
    }

    private fun getFromLocal() {
        coroutineScope.launch {
            val response = iGetMoviesShopCarUseCase.invoke()
            withContext(Dispatchers.Main) {
                response.collect {
                    movieList.value = it
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