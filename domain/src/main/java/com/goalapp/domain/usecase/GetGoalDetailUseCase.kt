package com.goalapp.domain.usecase

import com.goalapp.domain.repository.GoalRepository

class GetGoalDetailUseCase(
    private val goalRepository: GoalRepository
) {
    operator fun invoke(
        id: Int
    ) = goalRepository.getGoalDetail(id)


    /*
    operator fun invoke(
        scope: CoroutineScope
    ) {
        scope.launch(Dispatchers.IO) {
            goalRepository.getRepos()
        }
    }

     */
}