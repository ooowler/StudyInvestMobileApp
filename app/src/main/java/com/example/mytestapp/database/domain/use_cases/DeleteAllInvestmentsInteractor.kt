package com.example.mytestapp.database.domain.use_cases

import com.example.mytestapp.database.domain.repository.InvestmentRepository

class DeleteAllInvestmentsInteractor(
    private val repository: InvestmentRepository
) {
    suspend operator fun invoke() =
        repository.deleteAllInvestments()
}