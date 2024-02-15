package com.goalapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.goalapp.domain.repository.ThemePreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name =  "user_theme_preferences")

class ThemeRepositoryImpl@Inject constructor(
    private val context: Context
) : ThemePreferencesRepository {

    private val themeKey = intPreferencesKey("THEME_KEY")

    val theme : Flow<Int> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[themeKey] ?: 0
        }



    override suspend fun setAppTheme(theme: Int) {
        context.dataStore.edit { preferences ->
            preferences[themeKey] = theme
        }
    }
}