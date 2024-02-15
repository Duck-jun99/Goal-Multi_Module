package com.goalapp.presentation.view.goaldetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.goalapp.domain.model.GoalRepo
import com.goalapp.domain.usecase.CompleteGoalUseCase
import com.goalapp.domain.usecase.GetGoalDetailUseCase
import com.goalapp.domain.usecase.MinusStageUseCase
import com.goalapp.domain.usecase.PlusStageUseCase
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class RunningGoalDetailViewModel @AssistedInject constructor(

    @Assisted
    private var id: Int,
    getGoalDetailUseCase: GetGoalDetailUseCase,
    private val plusStageUseCase: PlusStageUseCase,
    private val minusStageUseCase: MinusStageUseCase,
    private val completeGoalUseCase: CompleteGoalUseCase


): ViewModel() {


    val item = getGoalDetailUseCase(id).stateIn(

        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun plusStage(id: Int) {
        plusStageUseCase(id, scope = viewModelScope)
    }

    fun minusStage(id: Int) {
        minusStageUseCase(id, scope = viewModelScope)
    }

    fun completeGoal(goal:GoalRepo){
        completeGoalUseCase(goal, scope = viewModelScope)
    }

    @AssistedFactory
    interface RunningGoalDetailViewModelFactory {
        fun create(id: Int): RunningGoalDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: RunningGoalDetailViewModelFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }

}