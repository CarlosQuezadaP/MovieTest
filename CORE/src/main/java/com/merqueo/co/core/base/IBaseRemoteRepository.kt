package com.merqueo.co.core.base

interface IBaseRemoteRepository<out R : IRemoteDataSource> {
    val remoteDataSource: R
}
