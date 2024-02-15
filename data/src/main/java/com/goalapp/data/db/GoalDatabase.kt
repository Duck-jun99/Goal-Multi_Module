package com.goalapp.data.db

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import com.goalapp.data.utils.Converters

@Database(
    entities = [GoalEntity::class],
    version = 1,
    /*
    autoMigrations = [
        AutoMigration (
            from = 1,
            to = 2,
            spec = GoalDatabase.MyAutoMigration::class
        )
    ],

     */
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class GoalDatabase: RoomDatabase() {

    abstract fun goalDao(): GoalDao
    class MyAutoMigration : AutoMigrationSpec

}