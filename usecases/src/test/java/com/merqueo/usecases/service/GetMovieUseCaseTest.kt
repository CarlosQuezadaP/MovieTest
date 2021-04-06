package com.merqueo.usecases.service

import com.merqueo.co.CORE.IConnectivity
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.data.repository.IMovieRepo
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.usecases.usecases.GetMoviesUseCase
import com.merqueo.co.usecases.usecases.IGetMoviesUseCase
import com.merqueo.usecases.builder.MovieBuilder
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetMovieUseCaseTest {

    lateinit var movieBuilder: MovieBuilder

    private val iMovieRepo: IMovieRepo = mockk()
    private val iConnectivity: IConnectivity = mockk()

    private val getAllMovies = flowOf(Resource.Success(MovieBuilder().buildAsList()))

    private val param_page = 1

    @Before
    fun setup() {
        coEvery { iMovieRepo.getAll(true, param_page) } returns getAllMovies
        every { iConnectivity.isConnected() } returns true
        movieBuilder = MovieBuilder()
    }

    @Test
    fun `getAllMovies return movies object success`() {
        //Arrange
        val movieUseCase: IGetMoviesUseCase = GetMoviesUseCase(iMovieRepo, iConnectivity)

        val expectedValue = Resource.Success(movieBuilder.buildAsList())

        runBlocking {
            //Act
            val response = movieUseCase.invoke(param_page)

            //Assert
            response.collect { resource: Resource<List<MovieItemDomain>> ->
                when (resource) {
                    is Resource.Success -> {
                        Assert.assertEquals(expectedValue, resource)
                    }
                }
            }
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


}