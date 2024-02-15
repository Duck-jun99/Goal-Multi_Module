package com.goalapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goalapp.data.ThemeRepositoryImpl
import com.goalapp.domain.model.GoalRepo
import com.goalapp.domain.repository.ThemePreferencesRepository
import com.goalapp.domain.usecase.DeleteGoalUseCase
import com.goalapp.domain.usecase.GetGoalListUseCase
import com.goalapp.domain.usecase.InsertGoalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getGoalReposUseCase: GetGoalListUseCase,
    private val themeRepositoryImpl: ThemeRepositoryImpl

): ViewModel(){


    val items = getGoalReposUseCase().stateIn(

        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val themeId = themeRepositoryImpl.theme

    suspend fun setAppTheme(theme:Int){
        themeRepositoryImpl.setAppTheme(theme)

    }

}