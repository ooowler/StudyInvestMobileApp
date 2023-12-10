package com.example.mytestapp.database.domain.repository

import com.example.mytestapp.database.data.InvestmentDao
import com.example.mytestapp.database.domain.model.Investment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InvestmentRepository(
    private val dao: InvestmentDao
) {
    suspend fun getAllInvestments(): List<Investment> =
        withContext(Dispatchers.IO) {
            dao.getAllInvestments()
        }

    suspend fun insertInvestment(investment: Investment) =
        withContext(Dispatchers.IO) {
            dao.insertInvestment(investment)
        }

    suspend fun getTotalInvestedBalance(): Double =
        withContext(Dispatchers.IO) {
            dao.getTotalInvestedBalance()
        }

    suspend fun findByUid(uid: Long): Investment =
        withContext(Dispatchers.IO) {
            dao.findByUid(uid)
        }

    suspend fun deleteAllInvestments() =
        withContext(Dispatchers.IO) {
            dao.deleteAllInvestments()
        }
}