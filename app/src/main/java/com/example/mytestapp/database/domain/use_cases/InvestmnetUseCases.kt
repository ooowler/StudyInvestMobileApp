package com.example.mytestapp.database.domain.use_cases

data class InvestmentUseCases(
    val getAllInvestment: GetAllInvestmentInteractor,
    val insertInvestment: InsertInvestmentInteractor,
    val getOneInvestment: FindByUidInteractor,
    val getTotalInvestedBalance: GetTotalInvestedBalanceInteractor
)