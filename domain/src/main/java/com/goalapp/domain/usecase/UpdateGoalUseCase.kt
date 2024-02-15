package com.goalapp.domain.usecase

import com.goalapp.domain.model.GoalRepo
import com.goalapp.domain.repository.GoalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PlusStageUseCase(
    private val goalRepository: GoalRepository) {

    operator fun invoke(
        id: Int,
        scope: CoroutineScope
    ) {
        scope.launch(Dispatchers.Main) {
            goalRepository.plusStage(id)

        }
    }
}

class MinusStageUseCase(
    private val goalRepository: GoalRepository) {

    operator fun invoke(
        id: Int,
        scope: CoroutineScope
    ) {
        scope.launch(Dispatchers.Main) {
            goalRepository.minusStage(id)

        }
    }
}

class CompleteGoalUseCase(
    private val goalRepository: GoalRepository){

    operator fun invoke(
        item: GoalRepo,
        scope: CoroutineScope
    ) {
        scope.launch(Dispatchers.Main) {
            goalRepository.completeGoal(item)

        }
    }
}