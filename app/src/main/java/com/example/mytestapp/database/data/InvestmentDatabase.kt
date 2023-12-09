package com.example.mytestapp.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytestapp.database.domain.model.Investment

@Database(entities = [Investment::class], version = 2)
abstract class InvestmentDatabase: RoomDatabase() {
    abstract val investmentDao: InvestmentDao

    companion object {
        val DATABASE_NAME = "INVESTMNET"
    }
}