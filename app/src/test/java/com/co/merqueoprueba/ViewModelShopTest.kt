package com.co.merqueoprueba

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.co.merqueoprueba.utils.MainCoroutineScopeRule
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.merqueoprueba.presentation.states.ShopViewState
import com.merqueo.co.merqueoprueba.presentation.viewModel.ViewModelShopping
import com.merqueo.co.usecases.usecases.IDeleteMoviesFromShopUseCase
import com.merqueo.co.usecases.usecases.IGetMoviesShopCarUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
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


    val flow = flow {
        emit(Resource.Loading)
        delay(10)
        emit(Resource.Success(movieBuilder.buildAsList()))
    }

    lateinit var viewModelShopping: ViewModelShopping


    @Mock
    private lateinit var mockObserver: Observer<ShopViewState>


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        movieBuilder = MovieBuilder()

        viewModelShopping =
            ViewModelShopping(iGetMoviesShopCarUseCase, iDeleteMoviesFromShopUseCase)
    }

    @Captor
    private lateinit var captor: ArgumentCaptor<ShopViewState>


    @Test
    fun `get movies from shop return list movieItem success`() {
        coroutineScope.runBlockingTest {

            //arrange
            Mockito.`when`(iGetMoviesShopCarUseCase.invoke())
                .thenReturn(flow)

            //Act
            val liveData = viewModelShopping.getFromLocal()
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