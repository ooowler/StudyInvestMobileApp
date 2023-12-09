package com.example.mytestapp.database.domain.use_cases

import com.example.mytestapp.database.domain.model.Investment
import com.example.mytestapp.database.domain.repository.InvestmentRepository


class FindByUidInteractor(
    private val repository: InvestmentRepository
) {
    suspend operator fun invoke(uid: Long): Investment =
        repository.findByUid(uid)
}
