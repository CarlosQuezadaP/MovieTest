package com.merqueo.co.core.base


interface IBaseRepository<out L : ILocalDataSource, out R : IRemoteDataSource> :
    IBaseLocalRepository<L>, IBaseRemoteRepository<R>
