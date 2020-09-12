package com.kang6264.daumbooksearch.di

import com.kang6264.daumbooksearch.data.datasource.RemoteDataSource
import com.kang6264.daumbooksearch.data.datasource.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataSourceModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl) : RemoteDataSource
}