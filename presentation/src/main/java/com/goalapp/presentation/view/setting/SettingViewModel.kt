package com.goalapp.presentation.view.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goalapp.data.ThemeRepositoryImpl
import com.goalapp.domain.model.GoalRepo
import com.goalapp.domain.repository.ThemePreferencesRepository
import com.goalapp.domain.usecase.DeleteGoalUseCase
import com.goalapp.domain.usecase.GetGoalListUseCase
import com.goalapp.domain.usecase.InsertGoalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val themeRepositoryImpl: ThemeRepositoryImpl

): ViewModel(){

    val theme = themeRepositoryImpl.theme.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    suspend fun setAppTheme(theme:Int){
        themeRepositoryImpl.setAppTheme(theme)

    }

}