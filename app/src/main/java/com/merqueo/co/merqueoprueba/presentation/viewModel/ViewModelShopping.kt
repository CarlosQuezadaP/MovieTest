package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.merqueoprueba.presentation.states.BooleanViewState
import com.merqueo.co.merqueoprueba.presentation.states.MovieViewState
import com.merqueo.co.merqueoprueba.utils.SingleLiveEvent
import com.merqueo.co.usecases.usecases.IDeleteMoviesFromShopUseCase
import com.merqueo.co.usecases.usecases.IGetMoviesShopCarUseCase
import kotlinx.coroutines.flow.map

class ViewModelShopping(
    private val iGetMoviesShopCarUseCase: IGetMoviesShopCarUseCase,
    private val iDeleteMoviesFromShopUseCase: IDeleteMoviesFromShopUseCase
) :
    ViewModel() {

    init {
        getFromLocal()

    }

    private val movieViewState =
        MovieViewState()

    private val booleanViewState =
        BooleanViewState()


    fun getFromLocal() = iGetMoviesShopCarUseCase.invoke()
        .map {
            when (it) {
                is Resource.Success -> {
                    movieViewState.copy(
                        loading = false,
                        data = it.data
                    )
                }
                is Resource.Error -> {
                    movieViewState.copy(loading = false, error = "Error")
                }
                is Resource.Loading -> {
                    movieViewState.copy(loading = true)
                }
                else -> movieViewState.copy(loading = false, error = "Error")

            }

        }.asLiveData()


    fun deleteAll() = iDeleteMoviesFromShopUseCase.invoke().map {
        when (it) {
            is Resource.Success -> {
                booleanViewState.copy(
                    loading = false,
                    data = it.data
                )
            }
            is Resource.Error -> {
                booleanViewState.copy(loading = false, error = "Error")
            }
            is Resource.Loading -> {
                booleanViewState.copy(loading = true)
            }
            else -> booleanViewState.copy(loading = false, error = "Error")

        }

    }.asLiveData()


}