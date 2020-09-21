package com.merqueo.co.core.base

interface IBaseLocalRepository<out L : ILocalDataSource> {
    val localDataSource: L
}
