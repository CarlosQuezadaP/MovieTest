package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.SingleLiveEvent
import com.merqueo.co.usecases.usecases.IMovieDetailUseCase
import com.merqueo.co.usecases.usecases.IUpdateMovieUseCase
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
class DetailViewModel(
    private val IMovieDetailUseCase: IMovieDetailUseCase,
    private val iUpdateMovieUseCase: IUpdateMovieUseCase
) :
    ViewModel() {

    var job: Job? = null

    var coroutineScope = CoroutineScope(Dispatchers.IO)
    val showLoading = ObservableBoolean()
    val movieChangeState = SingleLiveEvent<Boolean>()

    var movie = SingleLiveEvent<MovieItemDomain>()

    fun getMovie(idMovie: Int) {
        showLoading.set(true)
        job = coroutineScope.launch {
            val response = IMovieDetailUseCase.invoke(idMovie)
            showLoading.set(false)
            withContext(Dispatchers.Main) {
                movie.value = response
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


}