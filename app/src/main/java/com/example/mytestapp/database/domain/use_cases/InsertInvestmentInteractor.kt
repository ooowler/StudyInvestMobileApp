package com.example.mytestapp.database.domain.use_cases

import com.example.mytestapp.database.domain.model.Investment
import com.example.mytestapp.database.domain.repository.InvestmentRepository

class InsertInvestmentInteractor(
    private val repository: InvestmentRepository
) {
    suspend operator fun invoke(investment: Investment) =
        repository.insertInvestment(investment)
}