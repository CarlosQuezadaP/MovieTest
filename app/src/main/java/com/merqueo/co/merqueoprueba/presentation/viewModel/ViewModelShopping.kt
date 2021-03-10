package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.presentation.states.ShopViewState
import com.merqueo.co.merqueoprueba.utils.SingleLiveEvent
import com.merqueo.co.usecases.usecases.IDeleteMoviesFromShopUseCase
import com.merqueo.co.usecases.usecases.IGetMoviesShopCarUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ViewModelShopping(
    private val iGetMoviesShopCarUseCase: IGetMoviesShopCarUseCase,
    private val iDeleteMoviesFromShopUseCase: IDeleteMoviesFromShopUseCase
) :
    ViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.IO)
    val isEmpty = SingleLiveEvent<Boolean>()

    init {
        getFromLocal()

    }

    private val viewState =
        ShopViewState()


    fun getFromLocal() = iGetMoviesShopCarUseCase.invoke()
        .map {
            when (it) {
                is Resource.Success -> {
                    viewState.copy(
                        loading = false,
                        data = it.data
                    )
                }
                is Resource.Error -> {
                    viewState.copy(loading = false, error = "Error")
                }
                is Resource.Loading -> {
                    viewState.copy(loading = true)
                }
                else -> viewState.copy(loading = false, error = "Error")

            }

        }.asLiveData()


    fun deleteAll() {
        coroutineScope.launch {
            iDeleteMoviesFromShopUseCase.invoke()
        }
    }

}