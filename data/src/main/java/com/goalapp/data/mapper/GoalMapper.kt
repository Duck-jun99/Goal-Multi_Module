package com.goalapp.data.mapper

import com.goalapp.data.db.GoalEntity
import com.goalapp.domain.model.GoalRepo

object GoalMapper:Mapper<GoalEntity, GoalRepo> {
    override fun GoalEntity.mapToDomainModel(): GoalRepo =
        GoalRepo(
            id=id,
            bigGoal = bigGoal,
            smallGoal = smallGoal,
            stage=stage,
            makeTime = makeTime,
            completeTime = completeTime,
            completion = completion
        )

    override fun GoalRepo.mapFromDomainModel(): GoalEntity =
        GoalEntity(
            id=id,
            bigGoal = bigGoal,
            smallGoal = smallGoal,
            stage=stage,
            makeTime = makeTime,
            completeTime = completeTime,
            completion = completion
        )
}