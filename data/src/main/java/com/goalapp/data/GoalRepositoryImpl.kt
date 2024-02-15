package com.goalapp.data

import com.goalapp.data.db.GoalDao
import com.goalapp.data.db.GoalEntity
import com.goalapp.data.mapper.GoalMapper.mapFromDomainModel
import com.goalapp.data.mapper.GoalMapper.mapToDomainModel
import com.goalapp.domain.model.GoalRepo
import com.goalapp.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoalRepositoryImpl @Inject constructor(
    private val goalDao: GoalDao
):GoalRepository {
    override fun getGoalList(): Flow<List<GoalRepo>> = flow {
        //return getRepos()
        emitAll(goalDao.getAll().map { it.mapToDomainModel() })
    }

    override fun getRunningGoalList(): Flow<List<GoalRepo>> = flow{
        emitAll(goalDao.getRunningGoalList().map { it.mapToDomainModel() })
    }

    override fun getCompleteGoalList(): Flow<List<GoalRepo>> = flow {
        emitAll(goalDao.getCompleteGoalList().map { it.mapToDomainModel() })
    }

    override fun getGoalDetail(id: Int): Flow<GoalRepo> =flow {
        emitAll(goalDao.getGoalDetail(id).map { it.mapToDomainModel() })
    }

    override suspend fun insertItem(item: GoalRepo) {
        goalDao.insert(item.mapFromDomainModel())
    }

    override suspend fun deleteItem(item: GoalRepo) {
        goalDao.delete(item.mapFromDomainModel())
    }

    override suspend fun plusStage(id: Int) {
        goalDao.plusStage(id)
    }

    override suspend fun minusStage(id: Int) {
        goalDao.minusStage(id)
    }

    override suspend fun completeGoal(item: GoalRepo) {
        goalDao.completeGoal(item.mapFromDomainModel())
    }

    private fun List<GoalEntity>.mapToDomainModel() =
        map { it.mapToDomainModel() }
}