package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.presentation.states.MovieViewState
import com.merqueo.co.merqueoprueba.utils.SingleLiveEvent
import com.merqueo.co.merqueoprueba.presentation.viewModel.interfaces.IMovieViewModel
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
    ViewModel(), IMovieViewModel {

    val movieChangeState = SingleLiveEvent<Boolean>()
    var movieList = MutableLiveData<List<MovieItemDomain>>()


    var isLoading = ObservableBoolean()
    var failure = ObservableBoolean()


    private var job: Job? = null
    private var coroutineScope = CoroutineScope(Dispatchers.IO)

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



    suspend fun updateMovieState(movieItemDomain: MovieItemDomain) {
        coroutineScope.launch {
            val value = iUpdateMovieUseCase.invoke(movieItemDomain.id, movieItemDomain.onStore)
            withContext(Dispatchers.Main) {
                movieChangeState.value = value
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}