package com.example.mytestapp.database.domain.use_cases

import com.example.mytestapp.database.domain.repository.InvestmentRepository

class GetTotalInvestedBalanceInteractor(
    private val repository: InvestmentRepository
) {
    suspend operator fun invoke(): Double =
        repository.getTotalInvestedBalance()
}



