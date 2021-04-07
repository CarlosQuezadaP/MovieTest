package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.presentation.states.BooleanViewState
import com.merqueo.co.merqueoprueba.utils.SingleLiveEvent
import com.merqueo.co.usecases.usecases.IMovieDetailUseCase
import com.merqueo.co.usecases.usecases.IUpdateMovieUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class DetailViewModel(
    private val IMovieDetailUseCase: IMovieDetailUseCase,
    private val iUpdateMovieUseCase: IUpdateMovieUseCase
) :
    ViewModel() {

    var job: Job? = null

    var coroutineScope = CoroutineScope(Dispatchers.IO)
    val showLoading = ObservableBoolean()


    private val booleanViewState =
        BooleanViewState()

    var movie = SingleLiveEvent<MovieItemDomain>()



    fun getMovie(idMovie: Int) {
        showLoading.set(true)
        job = coroutineScope.launch {
            val response = IMovieDetailUseCase.invoke(idMovie)
            showLoading.set(false)
            withContext(Dispatchers.Main) {
                response.collect {
                    movie.value = it
                }
            }
        }
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