package com.merqueo.co.home.presentacion.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merqueo.co.core.SingleLiveEvent
import com.merqueo.co.home.domain.service.IServiceMovie
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MovieViewModel(
    private val iServiceMovie: IServiceMovie
) :
    ViewModel(), IMovieViewModel {

    var job: Job? = null

    var coroutineScope = CoroutineScope(Dispatchers.IO)
    val showLoading = ObservableBoolean()
    val movieChangeState = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<String>()
    var movieList = MutableLiveData<List<MovieItemDomain>>()


    init {
        showData()
    }

    override fun showData() {
        showLoading.set(true)
        job = coroutineScope.launch {
            val response = iServiceMovie.getMovies()
            showLoading.set(false)
            withContext(Dispatchers.Main) {
                flowOf(response)
                    .catch { throwable ->
                        showError.value = throwable.localizedMessage
                    }.collect { result ->
                        result.collect {
                            showError.value = null
                            movieList.value = it
                        }
                    }
            }
        }
    }


    suspend fun updateMovieState(movieItemDomain: MovieItemDomain) {
        coroutineScope.launch {
            val value = iServiceMovie.updateMovieState(movieItemDomain.id, movieItemDomain.onStore)
            withContext(Dispatchers.Main) {
                movieChangeState.value = value
            }

        }
    }


}