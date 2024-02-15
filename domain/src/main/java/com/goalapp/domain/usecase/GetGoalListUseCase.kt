package com.goalapp.domain.usecase

import com.goalapp.domain.repository.GoalRepository

class GetGoalListUseCase(private val goalRepository: GoalRepository) {

    operator fun invoke() = goalRepository.getGoalList()

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

class GetRunningGoalListUseCase(private val goalRepository: GoalRepository) {

    operator fun invoke() = goalRepository.getRunningGoalList()

}

class GetCompleteGoalListUseCase(private val goalRepository: GoalRepository) {

    operator fun invoke() = goalRepository.getCompleteGoalList()

}