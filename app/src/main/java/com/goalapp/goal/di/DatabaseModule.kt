package com.goalapp.goal.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.goalapp.data.db.GoalDao
import com.goalapp.data.db.GoalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /*
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `Book` (`id` INTEGER, `name` TEXT, " +
                    "PRIMARY KEY(`id`))")
        }
    }

     */

    @Provides
    @Singleton
    fun provideGoalDatabase(@ApplicationContext context: Context): GoalDatabase {
        return Room.databaseBuilder(
            context,
            GoalDatabase::class.java, "Goal-db"
        )
            //.addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGoalDao(goalDatabase: GoalDatabase):GoalDao
    = goalDatabase.goalDao()

}