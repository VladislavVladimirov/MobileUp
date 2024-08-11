package ru.test.mobileup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.test.mobileup.data.repository.CoinRepositoryImpl
import ru.test.mobileup.domain.repository.CoinRepository

import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsCoinRepository(impl: CoinRepositoryImpl): CoinRepository
}
