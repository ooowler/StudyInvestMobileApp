package com.example.mytestapp.database.di

import android.app.Application
import androidx.room.Room
import com.example.mytestapp.database.data.InvestmentDatabase
import com.example.mytestapp.database.domain.repository.InvestmentRepository
import com.example.mytestapp.database.domain.use_cases.FindByUidInteractor
import com.example.mytestapp.database.domain.use_cases.GetAllInvestmentInteractor
import com.example.mytestapp.database.domain.use_cases.GetTotalInvestedBalanceInteractor
import com.example.mytestapp.database.domain.use_cases.InsertInvestmentInteractor
import com.example.mytestapp.database.domain.use_cases.InvestmentUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideInvestmentDatabase(app: Application): InvestmentDatabase = Room.databaseBuilder(
        app,
        InvestmentDatabase::class.java,
        InvestmentDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideInvestmentRepository(db: InvestmentDatabase): InvestmentRepository =
        InvestmentRepository(db.investmentDao)

    @Provides
    @Singleton
    fun provideInvestmentUseCases(repository: InvestmentRepository): InvestmentUseCases =
        InvestmentUseCases(
            getAllInvestment = GetAllInvestmentInteractor(repository),
            insertInvestment = InsertInvestmentInteractor(repository),
            getOneInvestment = FindByUidInteractor(repository),
            getTotalInvestedBalance = GetTotalInvestedBalanceInteractor(repository),
        )
}