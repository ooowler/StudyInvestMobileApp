package com.example.mytestapp.database.domain.use_cases

import com.example.mytestapp.database.domain.model.Investment
import com.example.mytestapp.database.domain.repository.InvestmentRepository

class GetAllInvestmentInteractor(
    private val repository: InvestmentRepository
) {
    suspend operator fun invoke(): List<Investment> =
        repository.getAllInvestments()
}