package com.goalapp.presentation.view.addgoal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goalapp.domain.model.GoalRepo
import com.goalapp.domain.usecase.DeleteGoalUseCase
import com.goalapp.domain.usecase.GetGoalListUseCase
import com.goalapp.domain.usecase.InsertGoalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AddGoalViewModel @Inject constructor(
    getGoalReposUseCase: GetGoalListUseCase,
    private val insertGoalUseCase: InsertGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase

): ViewModel(){


    val items = getGoalReposUseCase().stateIn(

        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun insertItem(item:GoalRepo) {
        insertGoalUseCase(item, scope = viewModelScope)
    }

    fun deleteItem(item:GoalRepo){
        deleteGoalUseCase(item, scope = viewModelScope)
    }
}