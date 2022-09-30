package com.example.enforcmemntapp.di

import com.example.enforcmemntapp.data.source.local.preference.PreferenceRepository
import com.example.enforcmemntapp.data.source.local.preference.PreferenceRepositoryImpl
import com.example.enforcmemntapp.data.source.local.repository.UserDataRepository
import com.example.enforcmemntapp.data.source.local.repository.UserDataRepositoryImpl
import com.example.enforcmemntapp.data.source.remote.repository.EnforcementRepository
import com.example.enforcmemntapp.data.source.remote.repository.EnforcementRepositoryImpl
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
    abstract fun bindsEnforcementRepo(
        enforcementRepositoryImpl: EnforcementRepositoryImpl
    ): EnforcementRepository

    @Binds
    @Singleton
    abstract  fun bindsUserDataRepository(
        userDataRepositoryImpl: UserDataRepositoryImpl
    ):UserDataRepository

    @Binds
    @Singleton
    abstract fun bindsPreferenceStore(
        preferenceRepositoryImpl: PreferenceRepositoryImpl
    ):PreferenceRepository
}