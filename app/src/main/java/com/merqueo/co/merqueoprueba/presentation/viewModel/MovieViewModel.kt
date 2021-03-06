package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.presentation.states.BooleanViewState
import com.merqueo.co.merqueoprueba.presentation.states.MovieViewState
import com.merqueo.co.merqueoprueba.presentation.viewModel.interfaces.IMovieViewModel
import com.merqueo.co.usecases.usecases.IGetMoviesUseCase
import com.merqueo.co.usecases.usecases.IUpdateMovieUseCase
import kotlinx.coroutines.flow.map

class MovieViewModel(
    private val iGetMoviesUseCase: IGetMoviesUseCase,
    private val iUpdateMovieUseCase: IUpdateMovieUseCase
) :
    ViewModel(), IMovieViewModel {

    private val booleanViewState =
        BooleanViewState()

    var isLoading = ObservableBoolean()
    var failure = ObservableBoolean()
    var rv = ObservableBoolean()

    private val viewState =
        MovieViewState()

    init {
        showData()
    }


    override fun showData(): LiveData<MovieViewState> {
        isLoading.set(true)
        failure.set(false)
        rv.set(false)

        return iGetMoviesUseCase.invoke(1)
            .map {
                when (it) {
                    is Resource.Success -> {
                        isLoading.set(false)
                        rv.set(false)
                        viewState.copy(
                            loading = false,
                            data = it.data
                        )
                    }
                    is Resource.Error -> {
                        isLoading.set(false)
                        rv.set(true)
                        viewState.copy(loading = false, error = it.message)
                    }
                    is Resource.Loading -> {
                        isLoading.set(true)
                        failure.set(false)
                        rv.set(false)
                        viewState.copy(loading = true)
                    }
                    else -> {
                        isLoading.set(false)
                        failure.set(true)
                        rv.set(false)
                        viewState.copy(loading = false, error = "Error")
                    }
                }
            }.asLiveData()
    }


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