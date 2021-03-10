package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.lifecycle.*
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.utils.SingleLiveEvent
import com.merqueo.co.merqueoprueba.presentation.states.ShopViewState
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
    private var movieList = MutableLiveData<List<MovieItemDomain>>()
    val isEmpty = SingleLiveEvent<Boolean>()

    init {
        getFromLocal()
    }

    private val viewState =
        ShopViewState()


    fun getFromLocal(): LiveData<ShopViewState> {

        val agl = iGetMoviesShopCarUseCase.invoke()
            .map {

                when (it) {
                    is Resource.Success -> {
                        movieList.value = it.data
                        isEmpty.value = (it.data.size == 0)

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

            }.asLiveData(Dispatchers.Default + viewModelScope.coroutineContext)

        return agl

    }


    fun deleteAll() {
        coroutineScope.launch {
            iDeleteMoviesFromShopUseCase.invoke()
        }
    }

    fun getMoviesFromShop() = movieList
}