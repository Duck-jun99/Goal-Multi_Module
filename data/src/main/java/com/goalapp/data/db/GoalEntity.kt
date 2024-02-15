package com.goalapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Goal")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "Big_Goal") //칼럼명을 변수명과 같이 쓰려면 생략
    var bigGoal: String,

    @ColumnInfo(name = "Small_Goal")
    var smallGoal: List<String>,

    @ColumnInfo(name = "Stage")
    var stage: Int,

    @ColumnInfo(name = "Make_time")
    var makeTime: String,

    @ColumnInfo(name = "Complete_time")
    var completeTime: String?,

    @ColumnInfo(name = "Completion")
    var completion: Boolean,
) {
    //constructor() : this(null, "", "",false,-1,"")
}
