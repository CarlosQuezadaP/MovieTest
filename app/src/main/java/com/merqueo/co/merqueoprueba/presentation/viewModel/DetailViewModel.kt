package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.merqueo.co.usecases.usecases.IServiceMovie
import com.merqueo.co.usecases.usecases.IserviceDetail
import com.merqueo.co.usecases.presentacion.SingleLiveEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoroutinesApi
class DetailViewModel(
    private val iserviceDetail: IserviceDetail,
    private val iServiceMovie: IServiceMovie
) :
    ViewModel() {

    var job: Job? = null

    var coroutineScope = CoroutineScope(Dispatchers.IO)
    val showLoading = ObservableBoolean()
    val movieChangeState = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<String>()

    var movie = SingleLiveEvent<com.merqueo.co.domain.models.MovieItemDomain>()

    fun getMovie(idMovie: Int) {
        showLoading.set(true)
        job = coroutineScope.launch {
            val response = iserviceDetail.getMovie(idMovie)
            showLoading.set(false)
            withContext(Dispatchers.Main) {
                flowOf(response)
                    .collect {
                        movie.value = it
                    }


            }
        }
    }

    suspend fun updateMovieState(movieItemDomain: com.merqueo.co.domain.models.MovieItemDomain) {
        coroutineScope.launch {
            val value = iServiceMovie.updateMovieState(movieItemDomain.id, movieItemDomain.onStore)
            withContext(Dispatchers.Main) {
                movieChangeState.value = value
            }

        }
    }


}