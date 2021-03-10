package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.presentation.states.BooleanViewState
import com.merqueo.co.merqueoprueba.presentation.states.MovieViewState
import com.merqueo.co.merqueoprueba.presentation.viewModel.interfaces.IMovieViewModel
import com.merqueo.co.usecases.usecases.IGetMoviesUseCase
import com.merqueo.co.usecases.usecases.IUpdateMovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MovieViewModel(
    private val iGetMoviesUseCase: IGetMoviesUseCase,
    private val iUpdateMovieUseCase: IUpdateMovieUseCase
) :
    ViewModel(), IMovieViewModel {

    private val booleanViewState =
        BooleanViewState()

    var isLoading = ObservableBoolean()
    var failure = ObservableBoolean()

    private val viewState =
        MovieViewState()

    init {
        showData()
    }


    override fun showData() = iGetMoviesUseCase.invoke(1)
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


    fun updateMovieState(movieItemDomain: MovieItemDomain) =
        iUpdateMovieUseCase.invoke(movieItemDomain.id, movieItemDomain.onStore).map {
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