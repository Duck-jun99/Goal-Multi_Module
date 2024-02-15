package com.goalapp.domain.model

data class GoalRepo (
    var id: Int = 0,

    var bigGoal: String,


    var smallGoal: List<String>,

    var stage: Int,

    var makeTime: String,

    var completeTime: String?,

    var completion: Boolean,
)
