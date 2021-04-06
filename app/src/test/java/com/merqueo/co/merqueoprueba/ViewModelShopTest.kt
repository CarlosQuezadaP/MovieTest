package com.co.merqueoprueba

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.co.merqueoprueba.utils.MainCoroutineScopeRule
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.merqueoprueba.presentation.states.BooleanViewState
import com.merqueo.co.merqueoprueba.presentation.states.MovieViewState
import com.merqueo.co.merqueoprueba.presentation.viewModel.ViewModelShopping
import com.merqueo.co.usecases.usecases.IDeleteMoviesFromShopUseCase
import com.merqueo.co.usecases.usecases.IGetMoviesShopCarUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.*

class ViewModelShopTest {

    @Mock
    lateinit var iGetMoviesShopCarUseCase: IGetMoviesShopCarUseCase

    @Mock
    lateinit var iDeleteMoviesFromShopUseCase: IDeleteMoviesFromShopUseCase

    lateinit var movieBuilder: MovieBuilder

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()


    val flow_get_movies = flow {
        emit(Resource.Loading)
        delay(10)
        emit(Resource.Success(movieBuilder.buildAsList()))
    }

    val flow_delete_movies = flow {
        emit(Resource.Loading)
        delay(10)
        emit(Resource.Success(true))
    }


    lateinit var viewModelShopping: ViewModelShopping


    @Mock
    private lateinit var mockObserverMovies: Observer<MovieViewState>


    @Mock
    private lateinit var mockObserverDelete: Observer<BooleanViewState>


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        movieBuilder = MovieBuilder()

        viewModelShopping =
            ViewModelShopping(iGetMoviesShopCarUseCase, iDeleteMoviesFromShopUseCase)
    }

    @Captor
    private lateinit var captorMovie: ArgumentCaptor<MovieViewState>

    @Captor
    private lateinit var captorDelete: ArgumentCaptor<BooleanViewState>


    @Test
    fun `get movies from local return list movieItem success`() {
        coroutineScope.runBlockingTest {

            //arrange
            Mockito.`when`(iGetMoviesShopCarUseCase.invoke())
                .thenReturn(flow_get_movies)

            //Act
            val liveData = viewModelShopping.getFromLocal()
            liveData.observeForever(mockObserverMovies)


            //Assert
            Mockito.verify(mockObserverMovies)
                .onChanged(captorMovie.capture())

            Assert.assertEquals(true, captorMovie.value.loading)

            coroutineScope.advanceTimeBy(10)

            Mockito.verify(mockObserverMovies, Mockito.times(2))
                .onChanged(captorMovie.capture()) // onchange has been triggered twice


            Assert.assertEquals(
                "Los vengadores 4",
                captorMovie.value.data[0].title
            )
        }
    }

    @Test
    fun `delete all movies`() {

        coroutineScope.runBlockingTest {

            //arrange
            Mockito.`when`(iDeleteMoviesFromShopUseCase.invoke())
                .thenReturn(flow_delete_movies)

            //Act
            val liveData = viewModelShopping.deleteAll()
            liveData.observeForever(mockObserverDelete)


            //Assert
            Mockito.verify(mockObserverDelete)
                .onChanged(captorDelete.capture())

            Assert.assertEquals(true, captorDelete.value.loading)

            coroutineScope.advanceTimeBy(10)

            Mockito.verify(mockObserverDelete, Mockito.times(2))
                .onChanged(captorDelete.capture()) // onchange has been triggered twice


            Assert.assertEquals(
                true,
                captorDelete.value.data
            )
        }
    }


}