package com.goalapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao{

    @Query("SELECT * FROM Goal") // ACS DESC
    fun getAll(): Flow<List<GoalEntity>>

    @Query("SELECT * FROM GOAL WHERE completion = 0")
    fun getRunningGoalList():Flow<List<GoalEntity>>

    @Query("SELECT * FROM GOAL WHERE completion = 1")
    fun getCompleteGoalList():Flow<List<GoalEntity>>

    @Query("SELECT * FROM Goal WHERE id = :id")
    fun getGoalDetail(id: Int): Flow<GoalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: GoalEntity)

    @Delete
    suspend fun delete(contact: GoalEntity)

    @Query("UPDATE Goal SET stage = stage + 1 WHERE id = :id")
    suspend fun plusStage(id: Int)

    @Query("UPDATE Goal SET stage = stage - 1 WHERE id = :id")
    suspend fun minusStage(id: Int)

    @Update
    suspend fun completeGoal(contact: GoalEntity)

}