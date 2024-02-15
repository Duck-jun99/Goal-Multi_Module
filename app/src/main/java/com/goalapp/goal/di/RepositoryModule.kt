package com.goalapp.goal.di

import com.goalapp.data.GoalRepositoryImpl
import com.goalapp.domain.repository.GoalRepository
import com.goalapp.domain.repository.ThemePreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGoalRepository(repository: GoalRepositoryImpl):GoalRepository{
        return repository
    }

    @Singleton
    @Provides
    fun provideThemeRepository(repository: ThemePreferencesRepository):ThemePreferencesRepository{
        return repository
    }

}