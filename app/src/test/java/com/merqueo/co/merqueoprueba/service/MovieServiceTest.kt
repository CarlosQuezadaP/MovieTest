package com.merqueo.co.merqueoprueba.service

import com.merqueo.co.home.domain.service.IServiceMovie
import com.merqueo.co.infraestructura.source.remote.IMoviesRemoteSource
import org.junit.Before
import org.junit.Test
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieServiceTest {

    @InjectMocks
    lateinit var servicioCrearAlquiler: IServiceMovie

    @Mock
    lateinit var moviesRepository: IMoviesRemoteSource


    @Before
    fun initialization() {
        MockitoAnnotations.initMocks(this)
        Mockito.mock(IMoviesRemoteSource::class.java)
    }

    @Test
    suspend fun getAllMovies() {


    }

}