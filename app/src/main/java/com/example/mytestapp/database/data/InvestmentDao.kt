package com.example.mytestapp.database.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytestapp.database.domain.model.Investment

@Dao
interface InvestmentDao {
    @Query("SELECT * FROM investment")
    fun getAllInvestments(): List<Investment>

    @Query("SELECT * FROM investment WHERE name = :name")
    fun fundByName(name: String): List<Investment>

    @Query("SELECT * FROM investment WHERE name = :uid")
    fun fundByUid(uid: Long): Investment?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInvestment(investment: Investment): Long
}