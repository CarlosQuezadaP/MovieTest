package com.co.merqueoprueba.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.co.merqueoprueba.MovieBuilder
import com.co.merqueoprueba.utils.MainCoroutineScopeRule
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.merqueoprueba.presentation.states.MovieViewState
import com.merqueo.co.merqueoprueba.presentation.viewModel.MovieViewModel
import com.merqueo.co.usecases.usecases.IGetMoviesUseCase
import com.merqueo.co.usecases.usecases.IUpdateMovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.*

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class ViewModelMovieTest {

    @Mock
    lateinit var iGetMoviesUseCase: IGetMoviesUseCase

    @Mock
    lateinit var iUpdateMovieUseCase: IUpdateMovieUseCase

    lateinit var movieBuilder: MovieBuilder

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()


    val flow = flow {
        emit(Resource.Loading)
        delay(10)
        emit(Resource.Success(movieBuilder.buildAsList()))
    }

    lateinit var viewModelMovie: MovieViewModel


    @Mock
    private lateinit var mockObserver: Observer<MovieViewState>


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        movieBuilder = MovieBuilder()

        viewModelMovie =
            MovieViewModel(iGetMoviesUseCase, iUpdateMovieUseCase)
    }

    @Captor
    private lateinit var captor: ArgumentCaptor<MovieViewState>


    @Test
    fun `get movies from server return list movieItem success`() {
        coroutineScope.runBlockingTest {

            //arrange
            val param_page = 1
            Mockito.`when`(iGetMoviesUseCase.invoke(param_page))
                .thenReturn(flow)

            //Act
            val liveData = viewModelMovie.showData()

            liveData.observeForever(mockObserver)


            //Assert
            Mockito.verify(mockObserver)
                .onChanged(captor.capture())

            Assert.assertEquals(true, captor.value.loading)

            coroutineScope.advanceTimeBy(10)

            Mockito.verify(mockObserver, Mockito.times(2))
                .onChanged(captor.capture()) // onchange has been triggered twice


            Assert.assertEquals(
                "Los vengadores 4",
                captor.value.data[0].title
            )
        }
    }


}