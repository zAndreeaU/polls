package com.andreeau.pollcore.api

import com.andreeau.pollcore.repositories.PollRepository
import com.andreeau.pollcore.repositories.impl.PollRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPollRepository(
        implementation: PollRepositoryImpl
    ): PollRepository
}