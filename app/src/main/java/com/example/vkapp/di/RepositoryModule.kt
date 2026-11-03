package com.example.vkapp.di

import com.example.vkapp.data.repository.AppRepositoryImpl
import com.example.vkapp.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: AppRepositoryImpl): AppRepository
}