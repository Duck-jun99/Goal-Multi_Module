package com.goalapp.domain.repository

import com.goalapp.domain.model.GoalRepo
import kotlinx.coroutines.flow.Flow

interface GoalRepository {
    fun getGoalList(): Flow<List<GoalRepo>>

    fun getRunningGoalList(): Flow<List<GoalRepo>>
    fun getCompleteGoalList(): Flow<List<GoalRepo>>

    fun getGoalDetail(id: Int): Flow<GoalRepo>
    suspend fun insertItem(item:GoalRepo)
    suspend fun deleteItem(item:GoalRepo)
    suspend fun plusStage(id:Int)
    suspend fun minusStage(id:Int)
    suspend fun completeGoal(item:GoalRepo)
}