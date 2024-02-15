package com.goalapp.goal.di

import com.goalapp.domain.repository.GoalRepository
import com.goalapp.domain.usecase.CompleteGoalUseCase
import com.goalapp.domain.usecase.DeleteGoalUseCase
import com.goalapp.domain.usecase.GetCompleteGoalListUseCase
import com.goalapp.domain.usecase.GetGoalDetailUseCase
import com.goalapp.domain.usecase.GetGoalListUseCase
import com.goalapp.domain.usecase.GetRunningGoalListUseCase
import com.goalapp.domain.usecase.InsertGoalUseCase
import com.goalapp.domain.usecase.MinusStageUseCase
import com.goalapp.domain.usecase.PlusStageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetGoalListUseCase (repository: GoalRepository):GetGoalListUseCase{
        return GetGoalListUseCase(repository)
    }

    @Provides
    fun providesRunningGoalListUseCase (repository: GoalRepository):GetRunningGoalListUseCase{
        return GetRunningGoalListUseCase(repository)
    }

    @Provides
    fun providesCompleteGoalListUseCase (repository: GoalRepository):GetCompleteGoalListUseCase{
        return GetCompleteGoalListUseCase(repository)
    }

    @Provides
    fun providesGetGoalDetailUseCase (repository: GoalRepository):GetGoalDetailUseCase{
        return GetGoalDetailUseCase(repository)
    }

    @Provides
    fun providesInsertGoalUseCase(repository: GoalRepository):InsertGoalUseCase{
        return InsertGoalUseCase(repository)
    }

    @Provides
    fun providesDeleteGoalUseCase(repository: GoalRepository):DeleteGoalUseCase{
        return DeleteGoalUseCase(repository)
    }

    @Provides
    fun providesPlusStageGoalUseCase(repository: GoalRepository):PlusStageUseCase{
        return PlusStageUseCase(repository)
    }

    @Provides
    fun providesMinusStageGoalUseCase(repository: GoalRepository):MinusStageUseCase{
        return MinusStageUseCase(repository)
    }

    @Provides
    fun providesCompleteGoalUseCase(repository: GoalRepository):CompleteGoalUseCase{
        return CompleteGoalUseCase(repository)
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object AssistedInjectViewModelModule{
    @Provides
    fun providesGetGoalDetailUseCase (repository: GoalRepository):GetGoalDetailUseCase{
        return GetGoalDetailUseCase(repository)
    }
    @Provides
    fun providesPlusStageGoalUseCase(repository: GoalRepository):PlusStageUseCase{
        return PlusStageUseCase(repository)
    }

    @Provides
    fun providesMinusStageGoalUseCase(repository: GoalRepository):MinusStageUseCase{
        return MinusStageUseCase(repository)
    }

    @Provides
    fun providesCompleteGoalUseCase(repository: GoalRepository):CompleteGoalUseCase{
        return CompleteGoalUseCase(repository)
    }
}
