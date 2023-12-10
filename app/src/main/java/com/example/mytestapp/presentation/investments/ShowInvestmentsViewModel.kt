package com.example.mytestapp.presentation.investments

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.database.domain.model.Investment
import com.example.mytestapp.database.domain.use_cases.InvestmentUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowInvestmentsViewModel @Inject constructor(
    private val investmentUseCases: InvestmentUseCases
) : ViewModel() {
    private val _counterState = mutableStateOf(0)
    val counterState: State<Int> = _counterState

    private val _totalBalance = mutableStateOf(0.0)
    val totalBalance: State<Double> = _totalBalance

    private val _listState = mutableStateOf(listOf<Investment>())
    val listState: State<List<Investment>> = _listState

    val scope = viewModelScope

    init {
        scope.launch {
            _listState.value = investmentUseCases.getAllInvestment()
            _totalBalance.value = investmentUseCases.getTotalInvestedBalance()
        }
    }

    fun insertInvest(invest: Investment) {
        scope.launch {
            investmentUseCases.insertInvestment(invest)
        }
    }

    fun deleteAllInvestments() {
        scope.launch {
            investmentUseCases.deleteAllInvestments()
        }
    }
}

