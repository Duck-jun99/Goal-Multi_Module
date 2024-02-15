package com.goalapp.domain.repository


interface ThemePreferencesRepository{
    suspend fun setAppTheme(theme:Int)
}