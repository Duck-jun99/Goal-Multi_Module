package com.goalapp.domain.usecase

import com.goalapp.domain.repository.ThemePreferencesRepository

class ThemeUseCase(private val themePreferencesRepository: ThemePreferencesRepository) {
    //operator fun invoke() = themePreferencesRepository.setAppTheme()
}