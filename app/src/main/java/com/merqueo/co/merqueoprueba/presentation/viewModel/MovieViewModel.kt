package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.presentation.states.MovieViewState
import com.merqueo.co.merqueoprueba.utils.SingleLiveEvent
import com.merqueo.co.usecases.usecases.IGetMoviesUseCase
import com.merqueo.co.usecases.usecases.IUpdateMovieUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MovieViewModel(
    private val iGetMoviesUseCase: IGetMoviesUseCase,
    private val iUpdateMovieUseCase: IUpdateMovieUseCase
) :
    ViewModel() {

    var movieList = MutableLiveData<List<MovieItemDomain>>()

    val movieChangeState = SingleLiveEvent<Boolean>()
    val isEmpty = SingleLiveEvent<Boolean>()
    var isLoading = ObservableBoolean()
    var failure = ObservableBoolean()

    private var coroutineScope = CoroutineScope(Dispatchers.IO)

    private val shopViewState =
        MovieViewState()


    init {
        showData()
    }

    fun showData() = iGetMoviesUseCase.invoke(1).map {
        when (it) {
            is Resource.Success -> {
                movieList.value = it.data
                isEmpty.value = (it.data.size == 0)
                shopViewState.copy(
                    loading = false,
                    data = it.data
                )
            }
            is Resource.Error -> {
                shopViewState.copy(loading = false, error = "Error")
            }
            is Resource.Loading -> {
                shopViewState.copy(loading = true)
            }
            else -> shopViewState.copy(loading = false, error = "Error")
        }
    }.asLiveData(Dispatchers.Default + viewModelScope.coroutineContext)

    fun updateMovieState(movieItemDomain: MovieItemDomain) {
        val value = iUpdateMovieUseCase.invoke(movieItemDomain.id, movieItemDomain.onStore)
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                movieChangeState.value = value
            }
        }
    }


}