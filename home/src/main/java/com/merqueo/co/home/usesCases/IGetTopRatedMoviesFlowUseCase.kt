package com.merqueo.co.home.usesCases

import androidx.lifecycle.LiveData
import com.merqueo.co.models.ui.MovieItemUI

interface IGetTopRatedMoviesFlowUseCase : IUseCase.SingleFlowInOutList<LiveData<Int>, MovieItemUI>