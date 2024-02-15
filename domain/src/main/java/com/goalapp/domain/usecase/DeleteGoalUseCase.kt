package com.goalapp.domain.usecase

import com.goalapp.domain.model.GoalRepo
import com.goalapp.domain.repository.GoalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DeleteGoalUseCase(
    private val goalRepository: GoalRepository) {

    operator fun invoke(
        item: GoalRepo,
        scope: CoroutineScope
    ) {
        scope.launch(Dispatchers.Main) {
            goalRepository.deleteItem(item)

        }
    }
}