package com.kang6264.daumbooksearch.di

import com.kang6264.daumbooksearch.data.repository.RepositoryImpl
import com.kang6264.daumbooksearch.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRepository(repositoryImpl: RepositoryImpl) : Repository
}