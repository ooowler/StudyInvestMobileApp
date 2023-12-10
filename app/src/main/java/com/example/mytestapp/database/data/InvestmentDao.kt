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

    @Query("SELECT * FROM investment WHERE uid = :uid")
    fun findByUid(uid: Long): Investment

    @Query("SELECT SUM(price * count) FROM investment")
    fun getTotalInvestedBalance(): Double

    @Query("SELECT * FROM investment WHERE name = :name")
    fun fundByName(name: String): List<Investment>

    @Query("DELETE FROM investment")
    suspend fun deleteAllInvestments()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInvestment(investment: Investment): Long
}