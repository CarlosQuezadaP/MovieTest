package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.utils.SingleLiveEvent
import com.merqueo.co.merqueoprueba.presentation.viewModel.interfaces.IMovieViewModel
import com.merqueo.co.usecases.usecases.IGetMoviesUseCase
import com.merqueo.co.usecases.usecases.IUpdateMovieUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

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

    init {
        showData()
    }

    override fun showData() {
        isLoading.set(true)
        failure.set(false)
        job = coroutineScope.launch {
            val response = iGetMoviesUseCase.invoke(1)
            withContext(Dispatchers.Main) {
                response
                    .collect {
                        isLoading.set(false)
                        when (it) {
                            is Resource.Success -> {
                                if (it.data.size == 0) {
                                    failure.set(true)
                                } else {

                                    movieList.value = it.data
                                }
                            }
                            is Resource.Error -> {
                                failure.set(true)
                            }

                            is Resource.Loading -> {

                            }
                        }
                    }
            }
        }
    }

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